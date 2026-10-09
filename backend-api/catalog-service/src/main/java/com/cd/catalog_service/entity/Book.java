package com.cd.catalog_service.entity;

import com.cd.catalog_service.enums.BookStatus;
import com.cd.common_lib.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE books SET deleted_at = CURRENT_TIMESTAMP WHERE id = ? AND version = ?")
public class Book extends BaseEntity {
    @Column(unique = true)
    String isbn;

    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String titleNormalized;

    @Column(columnDefinition = "TEXT")
    String description;

    Integer publicationYear;
    String edition;
    String language;
    Integer pageCount;
    String coverUri;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BookStatus status = BookStatus.ACTIVE;

    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_book_categories",
                    columnNames = {"book_id", "category_id"}
            )
    )
    Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_book_authors",
                    columnNames = {"book_id", "author_id"}
            )
    )
    Set<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisher;
}
