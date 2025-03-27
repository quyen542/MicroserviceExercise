CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              transaction_id BINARY(16)  NOT NULL UNIQUE,
                              account_id BIGINT NOT NULL,
                              amount DECIMAL(20,2) NOT NULL,
                              transaction_type VARCHAR(50) NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              payment_info TEXT,
                              FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(20,2) NOT NULL,
                         description TEXT
);

INSERT INTO product (id, name, price, description) VALUES
-- Viettel Products
(1, 'Viettel Topup 50K', 50000, 'Nạp tiền điện thoại 50K'),
(2, 'Viettel Topup 100K', 100000, 'Nạp tiền điện thoại 100K'),
(3, 'Viettel Topup 200K', 200000, 'Nạp tiền điện thoại 200K'),

-- Vinaphone Products
(4, 'Vinaphone Topup 50K', 50000, 'Nạp tiền điện thoại 50K'),
(5, 'Vinaphone Topup 100K', 100000, 'Nạp tiền điện thoại 100K'),
(6, 'Vinaphone Topup 200K', 200000, 'Nạp tiền điện thoại 200K'),

-- Mobifone Products
(7, 'Mobifone Topup 50K', 50000, 'Nạp tiền điện thoại 50K'),
(8, 'Mobifone Topup 100K', 100000, 'Nạp tiền điện thoại 100K'),
(9, 'Mobifone Topup 200K', 200000, 'Nạp tiền điện thoại 200K'),

-- EVN Hanoi Products
(10, 'EVN Hanoi Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K'),
(11, 'EVN Hanoi Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K'),
(12, 'EVN Hanoi Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K'),

-- EVN HCM Products
(13, 'EVN HCM Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K'),
(14, 'EVN HCM Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K'),
(15, 'EVN HCM Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K'),

-- EVN Central Products
(16, 'EVN Central Electric Bill 100K', 100000, 'Thanh toán hóa đơn điện 100K'),
(17, 'EVN Central Electric Bill 200K', 200000, 'Thanh toán hóa đơn điện 200K'),
(18, 'EVN Central Electric Bill 500K', 500000, 'Thanh toán hóa đơn điện 500K'),

-- Shopee Voucher Products
(19, 'Shopee Voucher 100K', 100000, 'Mua voucher Shopee 100K'),
(20, 'Shopee Voucher 200K', 200000, 'Mua voucher Shopee 200K'),
(21, 'Shopee Voucher 500K', 500000, 'Mua voucher Shopee 500K'),

-- Lazada Voucher Products
(22, 'Lazada Voucher 100K', 100000, 'Mua voucher Lazada 100K'),
(23, 'Lazada Voucher 200K', 200000, 'Mua voucher Lazada 200K'),
(24, 'Lazada Voucher 500K', 500000, 'Mua voucher Lazada 500K'),

-- Tiki Voucher Products
(25, 'Tiki Voucher 100K', 100000, 'Mua voucher Tiki 100K'),
(26, 'Tiki Voucher 200K', 200000, 'Mua voucher Tiki 200K'),
(27, 'Tiki Voucher 500K', 500000, 'Mua voucher Tiki 500K');



