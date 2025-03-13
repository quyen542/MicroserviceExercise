CREATE TABLE category (
                          id bigint NOT NULL AUTO_INCREMENT,
                          name varchar(255) DEFAULT NULL,
                          PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE operator (
                          id bigint NOT NULL AUTO_INCREMENT,
                          logo_url varchar(255) DEFAULT NULL,
                          name varchar(255) DEFAULT NULL,
                          category_id bigint DEFAULT NULL,
                          PRIMARY KEY (id),
                          KEY FKbb937cn97ijr8ajmxdskmxbxs (category_id),
                          CONSTRAINT FKbb937cn97ijr8ajmxdskmxbxs FOREIGN KEY (category_id) REFERENCES category (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) NOT NULL,
                           `price` decimal(10,2) NOT NULL,
                           `description` text DEFAULT NULL,
                           `operator_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK_product_operator` (`operator_id`),
                           CONSTRAINT `FK_product_operator` FOREIGN KEY (`operator_id`) REFERENCES `operator` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Thêm dữ liệu vào bảng category
-- Thêm dữ liệu vào bảng category với ID xác định
INSERT INTO category (id, name) VALUES
                                    (1, 'Mobile'),
                                    (2, 'Electricity'),
                                    (3, 'Voucher');

-- Thêm dữ liệu vào bảng operator với ID xác định
INSERT INTO operator (id, name, logo_url, category_id) VALUES
-- Mobile Operators
(1, 'Viettel', 'https://logo-viettel.com', 1),
(2, 'Vinaphone', 'https://logo-vinaphone.com', 1),
(3, 'Mobifone', 'https://logo-mobifone.com', 1),

-- Electricity Operators
(4, 'EVN Hanoi', 'https://logo-evnhanoi.com', 2),
(5, 'EVN HCM', 'https://logo-evnhcm.com', 2),
(6, 'EVN Central', 'https://logo-evncentral.com', 2),

-- Voucher Operators
(7, 'Shopee Voucher', 'https://logo-shopee.com', 3),
(8, 'Lazada Voucher', 'https://logo-lazada.com', 3),
(9, 'Tiki Voucher', 'https://logo-tiki.com', 3);

-- Thêm dữ liệu vào bảng product với ID xác định
INSERT INTO product (id, name, price, description, operator_id) VALUES
-- Viettel Products
(1, 'Viettel Topup 50K', 50000, 'Nạp tiền điện thoại 50K', 1),
(2, 'Viettel Topup 100K', 100000, 'Nạp tiền điện thoại 100K', 1),
(3, 'Viettel Topup 200K', 200000, 'Nạp tiền điện thoại 200K', 1),

-- Vinaphone Products
(4, 'Vinaphone Topup 50K', 50000, 'Nạp tiền điện thoại 50K', 2),
(5, 'Vinaphone Topup 100K', 100000, 'Nạp tiền điện thoại 100K', 2),
(6, 'Vinaphone Topup 200K', 200000, 'Nạp tiền điện thoại 200K', 2),

-- Mobifone Products
(7, 'Mobifone Topup 50K', 50000, 'Nạp tiền điện thoại 50K', 3),
(8, 'Mobifone Topup 100K', 100000, 'Nạp tiền điện thoại 100K', 3),
(9, 'Mobifone Topup 200K', 200000, 'Nạp tiền điện thoại 200K', 3),

-- EVN Hanoi Products
(10, 'EVN Hanoi Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K', 4),
(11, 'EVN Hanoi Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K', 4),
(12, 'EVN Hanoi Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K', 4),

-- EVN HCM Products
(13, 'EVN HCM Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K', 5),
(14, 'EVN HCM Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K', 5),
(15, 'EVN HCM Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K', 5),

-- EVN Central Products
(16, 'EVN Central Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K', 6),
(17, 'EVN Central Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K', 6),
(18, 'EVN Central Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K', 6),

-- Shopee Voucher Products
(19, 'Shopee Voucher 100K', 100000, 'Mua voucher Shopee 100K', 7),
(20, 'Shopee Voucher 200K', 200000, 'Mua voucher Shopee 200K', 7),
(21, 'Shopee Voucher 500K', 500000, 'Mua voucher Shopee 500K', 7),

-- Lazada Voucher Products
(22, 'Lazada Voucher 100K', 100000, 'Mua voucher Lazada 100K', 8),
(23, 'Lazada Voucher 200K', 200000, 'Mua voucher Lazada 200K', 8),
(24, 'Lazada Voucher 500K', 500000, 'Mua voucher Lazada 500K', 8),

-- Tiki Voucher Products
(25, 'Tiki Voucher 100K', 100000, 'Mua voucher Tiki 100K', 9),
(26, 'Tiki Voucher 200K', 200000, 'Mua voucher Tiki 200K', 9),
(27, 'Tiki Voucher 500K', 500000, 'Mua voucher Tiki 500K', 9);



