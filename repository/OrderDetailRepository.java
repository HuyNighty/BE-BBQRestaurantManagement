package com.huynguyen.bbqrestaurantmanagement.repository;

import com.huynguyen.bbqrestaurantmanagement.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT new com.huynguyen.bbqrestaurantmanagement.dto.response.OrderDetailResponse(...) " +
            "FROM OrderDetail od " +
            "WHERE LOWER(od.menuItem.menuItemName) LIKE LOWER(CONCAT('%', :menuItemName, '%'))")
    List<OrderDetail> findByMenuItemName(@Param("menuItemName") String menuItemName);
}
