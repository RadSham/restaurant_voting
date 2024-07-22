Restaurant Voting API

A voting system for deciding where to have lunch.

2 types of users: admin and regular users<br/>
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)<br/>
Menu changes each day (admins do the updates)<br/>
Users can vote for a restaurant they want to have lunch at today<br/>
Only one vote counted per user<br/>
If user votes again the same day:<br/>
If it is before 11:00 we assume that he changed his mind.<br/>
If it is after 11:00 then it is too late, vote can't be changed<br/>
Each restaurant provides a new menu each day.<br/>

-------------------------------------------------------------
- Stack: [JDK 17](http://jdk.java.net/17/), Spring Boot 2.5, Lombok, H2, Caffeine Cache, Swagger/OpenAPI 3.0, Mapstruct, Liquibase 
- Run: `mvn spring-boot:run` in root directory.
-----------------------------------------------------
[REST API documentation](http://localhost:8080/swagger-ui.html)  
Креденшелы:
```
User:  user@yandex.ru / password
Admin: admin@gmail.com / admin
```
