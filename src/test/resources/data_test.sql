DELETE FROM order_table_items;
DELETE FROM item;
DELETE FROM product;
DELETE FROM order_table;
INSERT INTO product (id, name, category, weight, price, creation) VALUES
(1,'Camisa 1','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(2,'Camisa 2','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(3,'Camisa 3','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(4,'Camisa 4','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(5,'Camisa 5','Vestuario',40,40,TIMESTAMPADD(MONTH,-12, CURRENT_DATE())),
(6,'Camisa 6','Vestuario',40,40,TIMESTAMPADD(MONTH,-11, CURRENT_DATE())),
(7,'Camisa 7','Vestuario',40,40,TIMESTAMPADD(MONTH,-10, CURRENT_DATE())),
(8,'Camisa 8','Vestuario',40,40,TIMESTAMPADD(MONTH,-9, CURRENT_DATE())),
(9,'Camisa 9','Vestuario',40,40,TIMESTAMPADD(MONTH,-8, CURRENT_DATE())),
(10,'Camisa 10','Vestuario',40,40,TIMESTAMPADD(MONTH,-7, CURRENT_DATE())),
(11,'Camisa 11','Vestuario',40,40,TIMESTAMPADD(MONTH,-6, CURRENT_DATE())),
(12,'Camisa 12','Vestuario',40,40,TIMESTAMPADD(MONTH,-5, CURRENT_DATE())),
(13,'Camisa 13','Vestuario',40,40,TIMESTAMPADD(MONTH,-4, CURRENT_DATE())),
(14,'Camisa 14','Vestuario',40,40,TIMESTAMPADD(MONTH,-3, CURRENT_DATE())),
(15,'Camisa 15','Vestuario',40,40,TIMESTAMPADD(MONTH,-2, CURRENT_DATE())),
(16,'Camisa 16','Vestuario',40,40,TIMESTAMPADD(MONTH,-1, CURRENT_DATE())),
(17,'Camisa 17','Vestuario',40,40,CURRENT_DATE()),
(18,'Camisa 18','Vestuario',40,40,CURRENT_DATE());

INSERT INTO order_table (id, customer_name, contact, shipping_address, grand_total, order_date) VALUES (1, 'JOAO DA SILVA', '333 333 333', 'Rua Joao Nogueira', 1500.0, TIMESTAMPADD(YEAR,-1, CURRENT_DATE()));
INSERT INTO order_table (id, customer_name, contact, shipping_address, grand_total, order_date) VALUES (2, 'MIGUEL FIGUEREDO', '333 333 333', 'Rua Joao Nogueira', 1000.0, TIMESTAMPADD(MONTH ,-8, CURRENT_DATE()));


INSERT INTO item (id, cost, shipping_fee, tax_amount, product_id) VALUES
(1, 70, 10, 7, 1),
(2, 70, 10, 7, 2),
(3, 70, 10, 7, 3),
(4, 70, 10, 7, 4),
(5, 70, 10, 7, 5),
(6, 70, 10, 7, 6),
(7, 70, 10, 7, 7),
(8, 70, 10, 7, 8),
(9, 70, 10, 7, 9),
(10, 70, 10, 7, 10),
(11, 70, 10, 7, 11),
(12, 70, 10, 7, 12),
(13, 70, 10, 7, 13),
(14, 70, 10, 7, 14),
(15, 70, 10, 7, 15),
(16, 70, 10, 7, 16),
(17, 70, 10, 7, 17);


INSERT INTO order_table_items (order_table_id, items_id) VALUES
(1, 1), (1, 2), (1, 3),
(2,4), (2,5), (2,6), (2,10), (2,11), (2,12), (2,13), (2,14), (2,15), (2,16);
