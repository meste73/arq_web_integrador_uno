CREATE TABLE IF NOT EXISTS product (
	product_id int,
	name varchar(45),
	value float,
	CONSTRAINT pk_product_id PRIMARY KEY (product_id)
)