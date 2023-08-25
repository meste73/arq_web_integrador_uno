CREATE TABLE IF NOT EXISTS receipt_product (
	receipt_id int,
	product_id int,
	quantity int,
	CONSTRAINT pk_receipt_product_id PRIMARY KEY (receipt_id, product_id),
	CONSTRAINT fk_receipt_id FOREIGN KEY (receipt_id) REFERENCES receipt,
	CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES client
)