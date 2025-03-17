CREATE TABLE transaction_history (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     transaction_id BINARY(16) NOT NULL UNIQUE,
                                     source_account VARCHAR(255) NOT NULL,
                                     destination_account VARCHAR(255) NOT NULL,
                                     amount DECIMAL(19,2) NOT NULL,
                                     new_balance DECIMAL(19,2) NOT NULL,
                                     status VARCHAR(50) NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     payment_info TEXT
);