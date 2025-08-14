package com.huynguyen.bbqrestaurantmanagement.entity;

import com.huynguyen.bbqrestaurantmanagement.enums.StatusTable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_tables")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    Long tableId;

    @Column(nullable = false, unique = true, name = "table_name")
    String tableName;

    @Column(nullable = false, name = "capacity") // Sức chứa của bàn (số lượng người).
    Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    StatusTable status;
}
