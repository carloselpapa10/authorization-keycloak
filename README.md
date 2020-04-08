# Authorization Services using Keycloak
This repo implements the concept of Authorization Services using Keycloak as Authorization Server.

## Authorization Flow
You can see the presentation on this [file]().

## Prerequisites
- Java 8 or greater
- Apache Maven
- Git
- Docker
- Jq tool

## How to run it?
Run this Docker command to create a mysql database.
```sh
$ docker run -p 8090:8080 -e KEYCLOAK_USER=carloselpapa10 -e KEYCLOAK_PASSWORD=123456 jboss/keycloak
User: carloselpapa10
PWD: 123456
```

## Import the Realm in Keycloak
Once you have installed Keycloak, import the realm located on this [file]().

### Run the application
```sh
$ mvn spring-boot:run
```

### How to use it?
Get the access token value using jq tool.
```sh
$ export access_token=$(\
    curl -X POST http://localhost:8090/auth/realms/spring-boot-quickstart/protocol/openid-connect/token \
    -H 'Authorization: Basic Y2F2ZW5kYW5vYToxMjM0NTY=' \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alice&password=123456&grant_type=password&client_id=app-authz-rest-springboot&client_secret=b854f8c1-fc72-47ce-9299-b14ce004858e' | jq-win64.exe --raw-output '.access_token')'
```

>Note that the user used to get the token is alice. Change it to jdoe or cavendanoa to see different results when requesting the Resource Server.

Check if the access_token variable contains the requested token.
```sh
$ echo $access_token
```

Default Resource '/''
```sh
$ curl -v -X GET http://localhost:8888/ -H "Authorization: Bearer "$access_token
```

Default Resource '/api/resourcea'
```sh
$ curl -v -X GET http://localhost:8888/api/resourcea -H "Authorization: Bearer "$access_token
```

Default Resource '/api/resourceb'
```sh
$ curl -v -X GET http://localhost:8888/api/resourceb -H "Authorization: Bearer "$access_token
```

Premium Resource - Scope: View '/api/premium'
```sh
curl -v -X GET http://localhost:8888/api/premium -H "Authorization: Bearer "$access_token
```

Premium Resource - Scope: Delete '/api/premium'
```sh
curl -v -X DELETE http://localhost:8888/api/premium -H "Authorization: Bearer "$access_token
```

Admin Resource - Scope: Delete '/api/admin'
```sh
curl -v -X GET http://localhost:8888/api/admin -H "Authorization: Bearer "$access_token
```


### References
1. https://oauth.net/2/
2. [Course: Getting Started with OAuth2.0](https://app.pluralsight.com/)
3. [Keycloak - Authorization Services Guide](https://www.keycloak.org/docs/latest/authorization_services/index.html)
4. https://www.ekransystem.com/en/blog/rbac-vs-abac
5. [RedHat Policy Enforcement](https://access.redhat.com/documentation/en-us/red_hat_single_sign-on/7.1/html/authorization_services_guide/enforcer_overview)


### MSc Carlos Avendaño
[Linkedin](https://www.linkedin.com/in/carlos-alberto-avenda%C3%B1o-arango-534b0a137/)