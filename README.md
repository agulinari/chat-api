# ASAPP Chat Backend Challenge v1
### Overview
This is a java based boilerplate which runs an HTTP Server configured to answer the endpoints defined in 
[the challenge you received](https://backend-challenge.asapp.engineering/).
All endpoints are configured in src/main/java/com/asapp/backend/challenge/Application.java and if you go deeper to the
Routes and Filters passed as second parameters, you will find a TODO comment where you are free to implement your solution.

### How to run it
```
./gradlew run
```
### Build docker image
```
./gradlew build
docker build -t chat-api:1.0.0 .
```
### Run docker image
```
docker run -p 8080:8080 chat-api:1.0.0
```

### Deploy on k8s
```
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

##### Note
You can remove/modify this file for documenting your solution.

