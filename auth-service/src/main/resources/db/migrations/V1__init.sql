CREATE TABLE profile (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         profile_id BINARY(16) NOT NULL UNIQUE
);

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL,
                      profile_id BIGINT UNIQUE,
                      CONSTRAINT fk_user_profile FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE
);

INSERT INTO profile (id, profile_id)
VALUES (1, UUID_TO_BIN(UUID()));

INSERT INTO user (id, username, password, role, profile_id)
VALUES (1, 'quyen', '123', 'customer', 1);