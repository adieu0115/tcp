FROM openjdk:11

# 프로젝트 루트 디렉토리를 /app으로 복사
COPY . /app

# 소스 디렉토리로 이동해 컴파일
WORKDIR /app/src
RUN javac Main.java

# 애플리케이션 실행
CMD ["java", "Main"]
