# Sample REST API with Spring Boot, JPA and Hibernate for a Technical Test

## Steps to Setup

**1. Make sure you have Java 17 installed**

```bash
java -version
# java version "17.0.4.1"                                     
```

**2. Clone the application**
```bash
git clone git@github.com:Shapeqs/TechnicalTest.git
```


**3. Build and run the app using maven**

```bash
cd path/to/the/cloned/application/TechnicalTest
mvn package
java -jar target/technicaltest-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## API Reference

To see more details run the app and checkout the swagger at <http://localhost:8080/swagger-ui/index.html>
or you can checkout the Postman collection in  

### Get User Request

```http
  GET /api/v1/users/{id}
```

| Parameter | Type     | Description                               |
|:----------| :------- |:------------------------------------------|
| `id`      | `string` | **Required**. The id of the user to fetch |

### Get User Response

``` json
{
    "data": {
        "id": 8,
        "name": "Jean Marie",
        "birthdate": "2000-08-14",
        "country": "France",
        "phoneNumber": "0123456789",
        "gender": "Male"
    },
    "message": "",
    "status": 200
}
```

### Post a new user Request

```http
  POST /api/v1/users
```

#### Body

``` json
{
  "name": "Jean Marie",
  "birthdate": "2000-08-14",
  "country": "France",
  "gender": "Male",
  "phoneNumber": "0123456789"
}
```

### Post a new user Response

``` json
{
    "data": {
        "id": 8,
        "name": "Jean Marie",
        "birthdate": "2000-08-14",
        "country": "France",
        "phoneNumber": "0123456789",
        "gender": "Male"
    },
    "message": "User Created",
    "status": 201
}
```

## Author

- [Clément Querre](https://www.github.com/Shapeqs)

## Feedback

If you have any feedback, please reach out to us at [clementquerrepro@gmail.com](mailto:clementquerrepro@gmail.com)

