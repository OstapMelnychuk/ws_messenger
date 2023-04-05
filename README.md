# ws_messenger
## Description
This is pet project that is created for practising with Spring Boot WebSockets.
## How to launch?
Clone the project and download all the dependencies from maven.
This project is using two databases: MySql and MongoDb.
### MySql setup
To install MySql visit a link: https://www.mysql.com/downloads/

### MongoDB setup
If you have docker, go to project root and open terminal. Enter command:
```docker-compose up```

This command will install a Mongodb instance in your docker.
To configure database credentials, edit them in **docker-compose.yaml** file:
```
      - MONGO_INITDB_ROOT_USERNAME=
      - MONGO_INITDB_ROOT_PASSWORD=
      - _ME_CONFIG_MONGODB_ADMINUSERNAME=
      - _ME_CONFIG_MONGODB_ADMINPASSWORD=
```
If you don`t have MongoDb visit https://www.mongodb.com/try/download/community

### Configuration
Open application properties and fill this fields with you db credentials.

_For MySql:_
```
      spring.datasource.username=
      spring.datasource.password=
```
_For MongoDb:_
```
      spring.data.mongodb.username=
      spring.data.mongodb.password=
```

**And as a final step just run your application!**
