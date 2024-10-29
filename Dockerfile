# 1. Java 환경을 위한 Docker 이미지를 선택
FROM openjdk:11-jdk-slim

# 2. 작업 디렉터리 설정 후 프로젝트 파일 복사
WORKDIR /app
COPY . /app

# 3. Java 파일 컴파일 (Server.java의 경우)
RUN javac Server.java

# 4. 컨테이너 시작 시 실행할 명령어
CMD ["java", "Server"]
