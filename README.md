# Tenpo Challenge

Technical test for Tenpo

## Building and run Docker
First build a docker image
```
./mvnw package verify jib:dockerBuild
```
Then run:
```
docker-compose -f src/docker/app.yml up -d
```


### Data persistence is preferred

add the volume mapping in the file  src/docker/app.yml

Example
```
   volumes:
      - ~/data/:/var/lib/postgresql/data/
```
And modifies the propertie hibernate.ddl-auto in application.yml

Example
```
    hibernate:
      ddl-auto: update
```
## Reference Documentation

* [Swagger-UI](http://localhost:8080/swagger-ui/index.html)

* [Postman collection](doc/Tenpo Challenge.postman_collection.json)
