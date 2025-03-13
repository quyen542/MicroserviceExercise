CREATE DATABASE IF NOT EXISTS auth_service;
CREATE DATABASE IF NOT EXISTS payment_service;
CREATE DATABASE IF NOT EXISTS vas_service;

-- Tạo user và cấp quyền (nếu cần)
GRANT ALL PRIVILEGES ON auth_service.* TO 'myuser'@'%';
GRANT ALL PRIVILEGES ON payment_service.* TO 'myuser'@'%';
GRANT ALL PRIVILEGES ON vas_service.* TO 'myuser'@'%';

FLUSH PRIVILEGES;