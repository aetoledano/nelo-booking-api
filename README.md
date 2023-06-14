## Nelo booking api

### How to run

There is a docker compose file in the project containing a postgres db container it binds by default to port 5432 start
with

```shell
docker compose up
```

The project was build using ItelliJ it can be run directly from the ide with Java17 or use maven with default Java17 on system path
```shell
mvn spring-boot:run
```

The application variable **spring.sql.init.mode** should be set to **never** after initial execution or application will fail to start second time

Server run on port 8080

### Postman                     

There is an included postman collection to try the 3 endpoints 

```shell
POST /restaurants
POST /reservations
DELETE /reservations/<reservationId>
```
    
### Next steps

- Complete the tests suite for services and controllers
- Adds input validations like maximum hour time for starting a reservation, reservations made in the past, some data types like dates format, refactor the endpoints response to hide irrelevant implementation details in DTOs 
- What happens to reservations that start and finish the next day
