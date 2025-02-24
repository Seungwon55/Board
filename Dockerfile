# 자바 17 베이스 이미지
FROM openjdk:17

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너 내부로 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 컨테이너에서 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0"]

# 컨테이너에서 사용할 포트 노출
EXPOSE 8080