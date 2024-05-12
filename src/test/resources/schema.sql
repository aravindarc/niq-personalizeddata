
CREATE SEQUENCE IF NOT EXISTS shelf_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE product
(
    product_id VARCHAR(255) NOT NULL,
    category   VARCHAR(255),
    brand      VARCHAR(255),
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

CREATE TABLE shelf
(
    id                 BIGINT NOT NULL DEFAULT NEXTVAL('shelf_seq'),
    shopper_id         VARCHAR(255),
    product_product_id VARCHAR(255),
    relevancy_score    DOUBLE PRECISION,
    CONSTRAINT pk_shelf PRIMARY KEY (id)
);

ALTER TABLE shelf
    ADD CONSTRAINT FK_SHELF_ON_PRODUCT_PRODUCTID FOREIGN KEY (product_product_id) REFERENCES product (product_id);