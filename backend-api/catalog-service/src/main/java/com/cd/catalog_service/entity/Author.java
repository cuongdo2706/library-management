package com.cd.catalog_service.entity;

import com.cd.common_lib.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity {
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String nameNormalized;

    @Column(columnDefinition = "TEXT")
    String biography;
}
