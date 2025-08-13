DROP TABLE IF EXISTS ASSET;
CREATE TABLE IF NOT EXISTS ASSET(customer_id BIGINT NOT NULL,
                    asset_name VARCHAR(50),
                    size NUMERIC(20,2),
                    usable_size NUMERIC(20,2));

DROP TABLE IF EXISTS ORDER;
CREATE TABLE IF NOT EXISTS ORDER(    customer_id BIGINT NOT NULL,
                                     asset_name VARCHAR(50),
                                     order_side VARCHAR(50),
                                     size NUMERIC(20,2),
                                     price NUMERIC(20,2),
                                     status VARCHAR(50),
                                     create_date DATE );

DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE IF NOT EXISTS CUSTOMER(customer_id BIGINT NOT NULL,
                                    customer_name VARCHAR(50),
                                    user_name VARCHAR(50),
                                    password VARCHAR(50),
                                    authority VARCHAR(50));

INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (1, 'ARCELIK', 21.5,100);
INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (1, 'TRY', NULL,500);

INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (2, 'SASA POLYESTER', 15.5,110);
INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (2, 'TRY', NULL,500);

INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (3, 'ABC MAKINE KIMYA', 10.3,120);
INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (3, 'TRY', NULL,500);

INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (4, 'AKBANK', 12.4,150);
INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (4, 'TRY', NULL,500);

INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (5, 'SABANCI HOLDING', 8.2,200);
INSERT INTO ASSET (customer_id, asset_name, size, usable_size) VALUES (5, 'TRY', NULL,500);


INSERT INTO ORDER (customer_id, asset_name, order_side, size, price, status, create_date) VALUES (1, 'ARCELIK', 'BUY', 5.1, 1.2,'PENDING',CURRENT_DATE-1);
INSERT INTO ORDER (customer_id, asset_name, order_side, size, price, status, create_date) VALUES (2, 'SASA POLYESTER', 'BUY', 1.5, 0.6,'PENDING',CURRENT_DATE-2);
INSERT INTO ORDER (customer_id, asset_name, order_side, size, price, status, create_date) VALUES (3, 'ABC MAKINE KIMYA', 'BUY', 0.4, 2.56,'PENDING',CURRENT_DATE-3);
INSERT INTO ORDER (customer_id, asset_name, order_side, size, price, status, create_date) VALUES (4, 'AKBANK', 'BUY', 0.75, 4.25,'PENDING',CURRENT_DATE-4);
INSERT INTO ORDER (customer_id, asset_name, order_side, size, price, status, create_date) VALUES (5, 'SABANCI HOLDING', 'BUY', 3.8, 1.75,'PENDING',CURRENT_DATE-5);


INSERT INTO CUSTOMER (customer_id, customer_name, user_name, password, authority) VALUES (1, 'TOLGA GUZEL', 'tg', '1', 'ADMIN');
INSERT INTO CUSTOMER (customer_id, customer_name, user_name, password, authority) VALUES (2, 'AHMET BALTA', 'ab', '2', 'USER');
INSERT INTO CUSTOMER (customer_id, customer_name, user_name, password, authority) VALUES (3, 'FIKRI ZIYA', 'fz', '3', 'USER');
INSERT INTO CUSTOMER (customer_id, customer_name, user_name, password, authority) VALUES (4, 'MEHMET BOR', 'mb', '4', 'USER');
INSERT INTO CUSTOMER (customer_id, customer_name, user_name, password, authority) VALUES (5, 'ZIKRULLAH MARAL', 'zm', '5', 'USER');
commit;