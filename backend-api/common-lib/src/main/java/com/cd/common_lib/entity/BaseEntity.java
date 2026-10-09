package com.cd.common_lib.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    Instant modifiedAt;
}
