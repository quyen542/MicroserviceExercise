-- Tạo bảng account
CREATE TABLE account (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         profile_id BINARY(16) NOT NULL UNIQUE,
                         account_number VARCHAR(255) NOT NULL,
                         account_type VARCHAR(50) NOT NULL,
                         balance DECIMAL(20, 2) NOT NULL,
                         version INT DEFAULT 0 NOT NULL
);

-- Thêm dữ liệu mẫu
INSERT INTO account (id, profile_id, account_number, account_type, balance, version)
VALUES (1, UUID_TO_BIN('b2a1401e-ff13-11ef-bd65-0242ac120002'), '0907778888', 'EDA', 1000000.00, 0);

INSERT INTO account (id, profile_id, account_number, account_type, balance, version)
VALUES (2, UUID_TO_BIN('77af9623-ac3d-4893-ab0d-684084068723'), '0909999999', 'IDA', 1000000.00, 0);
