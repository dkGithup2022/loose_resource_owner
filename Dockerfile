# Dockerfile
FROM mysql:8.0

# MySQL 설정
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=db

# 포트 설정 (기본 MySQL 포트는 3306)
EXPOSE 3306

CMD ["mysqld"]


# docker build -t my-mysql .
# docker run -d -p 3306:3306 --name my-mysql-container --cpus="2.0" my-mysql