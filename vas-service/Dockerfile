# 1️⃣ Chọn base image (ở đây dùng OpenJDK 17)
FROM openjdk:17-jdk-slim

# 2️⃣ Đặt thư mục làm việc bên trong container
WORKDIR /app

# 3️⃣ Sao chép file JAR từ máy vào container
COPY build/libs/vas-service-0.0.1-SNAPSHOT.jar app.jar

# 4️⃣ Lệnh chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]