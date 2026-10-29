CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE TABLE IF NOT EXISTS authors
(
    id              UUID                           NOT NULL,
    created_at      TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    modified_at     TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    name            VARCHAR(255)                   NOT NULL,
    name_normalized VARCHAR(255)                   NOT NULL,
    biography       TEXT,
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book_authors
(
    author_id UUID NOT NULL,
    book_id   UUID NOT NULL,
    CONSTRAINT pk_book_authors PRIMARY KEY (author_id, book_id)
);

CREATE TABLE IF NOT EXISTS book_categories
(
    book_id     UUID NOT NULL,
    category_id UUID NOT NULL,
    CONSTRAINT pk_book_categories PRIMARY KEY (book_id, category_id)
);

CREATE TABLE IF NOT EXISTS books
(
    id               UUID                           NOT NULL,
    created_at       TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    modified_at      TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    isbn             VARCHAR(255),
    title            VARCHAR(255)                   NOT NULL,
    title_normalized VARCHAR(255)                   NOT NULL,
    description      TEXT,
    publication_year INTEGER,
    edition          VARCHAR(255),
    language         VARCHAR(255),
    page_count       INTEGER,
    cover_uri        VARCHAR(255),
    status           VARCHAR(255)                   NOT NULL,
    publisher_id     UUID,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories
(
    id              UUID                           NOT NULL,
    created_at      TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    modified_at     TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    name            VARCHAR(255)                   NOT NULL,
    name_normalized VARCHAR(255)                   NOT NULL,
    description     TEXT,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS publishers
(
    id              UUID                           NOT NULL,
    created_at      TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    modified_at     TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    name            VARCHAR(255)                   NOT NULL,
    name_normalized VARCHAR(255)                   NOT NULL,
    address         VARCHAR(255),
    CONSTRAINT pk_publishers PRIMARY KEY (id)
);

CREATE INDEX idx_books_title_normalized_trgm
    ON books USING GIN (title_normalized gin_trgm_ops);

CREATE INDEX idx_categories_name_normalized_trgm
    ON categories USING GIN (name_normalized gin_trgm_ops);

CREATE INDEX idx_authors_name_normalized_trgm
    ON authors USING GIN (name_normalized gin_trgm_ops);

CREATE INDEX idx_publishers_name_normalized_trgm
    ON publishers USING GIN (name_normalized gin_trgm_ops);

CREATE INDEX idx_books_created_at
    ON books (created_at);

ALTER TABLE books
    ADD CONSTRAINT uk_books_isbn UNIQUE (isbn);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_PUBLISHER FOREIGN KEY (publisher_id) REFERENCES publishers (id);

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_author FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE book_authors
    ADD CONSTRAINT fk_booaut_on_book FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE book_categories
    ADD CONSTRAINT fk_boocat_on_book FOREIGN KEY (book_id) REFERENCES books (id);

ALTER TABLE book_categories
    ADD CONSTRAINT fk_boocat_on_category FOREIGN KEY (category_id) REFERENCES categories (id);