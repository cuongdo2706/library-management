package com.cd.core_service.entity;


import com.cd.core_service.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity {
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String nameNormalized;
    @Column(columnDefinition = "TEXT")
    String description;
}
