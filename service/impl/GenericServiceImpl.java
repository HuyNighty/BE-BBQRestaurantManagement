package com.huynguyen.bbqrestaurantmanagement.service.impl;

import com.huynguyen.bbqrestaurantmanagement.enums.error.ErrorCode;
import com.huynguyen.bbqrestaurantmanagement.exception.AppException;
import com.huynguyen.bbqrestaurantmanagement.service.GenericService;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    protected final JpaRepository<T, ID> jpaRepository;

    @Override
    @Transactional
    public T save(T entity) {
        log.info("Saving entity: {}", entity.getClass().getSimpleName());

        ID id = extractId(entity);

        if (id != null && jpaRepository.existsById(id)) {
            log.warn("Entity already exists with ID: {}", id);
            throw new AppException(ErrorCode.DUPLICATE_RESOURCE);
        }

        return jpaRepository.save(entity);
    }

    @SuppressWarnings("unchecked") // Để bỏ qua cảnh báo của trình biên dịch (compiler) -> Vì ép kiểu không an toàn (unchecked cast) đáng ra field.get(entity); sẽ trả về một Object nhưng ta đã ép kiểu thành ID. -> Nếu không có annotation này thì IDE sẽ hiện warning.
    private ID extractId(T entity) {
        try {
            // Lấy class của đối tương entity.
            // ex: entity = new Permission(); -> class = Permission.class
            Class<?> clazz = entity.getClass();

            while (clazz != null) { // Duyệt qua class hiện tại và các lớp cha (super class nếu có).

                for (Field field : clazz.getDeclaredFields()) { // Lấy tất cả field trong class đó (Dù cho nó là private hay protected).

                    if (field.isAnnotationPresent(Id.class)) { // Tìm field có annotation là @Id.

                        field.setAccessible(true); // Bỏ giới hạn truy cập của field (private, protected).
                        // Vì private và protected chỉ có thể truy cập được trong cùng class hoặc subclass, nên class GenericServiceImpl về mặt kỹ thuật (không thể truy cập trực tiếp) vào field đó nếu không dùng reflection.

                        return (ID) field.get(entity); // Lấy giá trị của field đó trong đối tượng entity. -> Ép về kiểu ID.
                    }
                }

            // Nếu chưa tìm thấy field có @Id trong class hiện tại, tiếp tục tìm ở superclass (lớp cha).
            // Lý do: Có nhiều hệ thống sử dụng mô hình kế thừa, trong đó @Id thường được đặt ở một lớp cha trừu tượng, ví dụ:
            // abstract class BaseEntity {
            //     @Id
            //     private String id;
            // }
            // → Các entity con chỉ cần extends BaseEntity mà không cần khai báo lại @Id. Nếu không duyệt lên superclass, hệ thống sẽ không thể nhận diện đúng ID → gây lỗi trong quá trình lưu, cập nhật entity.
                clazz = clazz.getSuperclass();
            }

            // Nếu không tìm thấy @Id thì quăng lỗi rõ ràng.
            throw new RuntimeException("No @Id field found in class " + entity.getClass().getSimpleName());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to access @Id field: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public T update(ID id, T updatedEntity) {
        log.info("Updating entity ID: {}", id);

        // Tìm entity có trong DB.
        T existingEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));

        // Sao chép dữ liệu từ người nhập. Hay còn gọi là thay đổi dữ liệu.
        copyNonNullProperties(updatedEntity, existingEntity);

        // Lưu entity đã được cập nhật vào DB.
        return jpaRepository.save(existingEntity);
    }

    // Nhiệm vụ chính của method này là sao chép các thuộc tính từ source sang target.
    protected void copyNonNullProperties(T source, T target) {
        // source: Dữ liệu ta nhập vào. -- updateEntity.
        // target: Dữ liệu ban đầu. -- existingEntity.
        // Ví dụ: userName: Huy, yob: 2006, sex: male -> target.
        // input: userName: Nguyễn Huy ( lúc này ta chỉ cập nhật userName nên yob và sex sẽ là null) -> source.

        // getNullPropertyNames(source) trong trường hợp này nó sẽ trả về các dữ liệu null.
        // Khi ta gọi copyProperties nó sẽ bỏ qua và giữ nguyên các dữ liệu null và bắt đầu cập nhật.
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

        // Nếu không có method này và getNullPropertyNames.
        // -> Thì: Đề userName: Huy, yob: 2006, sex: male.
        // input: userName: Nguyễn Huy.
        // output: userName: Nguyễn Huy, yob: null, sex: null.
        // Nếu có đầy đủ method.
        // input: userName: Nguyễn Huy.
        // output: userName: Nguyễn Huy, yob: 2006, sex: 2006.
    }

    // Trả về một mảng chứa các dữ liệu null.
    private String[] getNullPropertyNames(Object source) { // source: updateEntity.

        // Bộc đối tượng bằng BeanWrapper.
        final BeanWrapper wrapperSource = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();

        // Lấy tấy cả các mô tả thuộc tính của đối tượng.
        PropertyDescriptor[] pds = wrapperSource.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) { // Duyệt các thuộc tính của updateEntity

            // Lấy tất cả các thuộc tính có trong updateEntity.
            Object srcValue = wrapperSource.getPropertyValue(pd.getName());

            // Lấy các thuộc tính null.
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        // Chuyển Set thành mảng String và trả về.
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        log.info("Deleting entity with ID: {}", id);
        if (!jpaRepository.existsById(id)) {
            log.warn("Delete failed: Entity with ID {} not found.", id);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        } else {
            jpaRepository.deleteById(id);
            log.info("Entity with ID {} deleted successfully.", id);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        log.debug("Finding entity by ID: {}", id);
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        log.debug("Finding all entities.");
        return jpaRepository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        log.debug("Checking existence of entity with ID: {}", id);
        return jpaRepository.existsById(id);
    }
}
