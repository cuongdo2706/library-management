package com.cd.catalog_service.entity;

import com.cd.common_lib.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Publisher extends BaseEntity {
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String nameNormalized;
    String address;
}
