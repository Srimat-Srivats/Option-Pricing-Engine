FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY option-pricing-engine.jar app.jar
CMD ["java","-jar","app.jar","100","100","0.05","0.2","1","252","200000","CALL","EURO"]
