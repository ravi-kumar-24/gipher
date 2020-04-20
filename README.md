# Gipher - A Capsule Case Study

## Problem Statement

Build a system to manage and recommend GIFs to a user. Refer https://giphy.com/

## Requirements:
1. The application needs to fetch gifs from https://developers.giphy.com/docs/
2. A frontend where the user can search, view and favouriteGIF gifs.
3. A user can add a gif to favouriteGIF and should be able to view favouriteGIF list.
3. A recommendation service should be able to store all the unique bookmarked gifs from all the users and maintain counter for number of users added a particular gifs into favouriteGIF list.
4. An option to view recommended gifs should be available on frontend. 

## Microservice developed
1. AccountManager : http://[IP/localhost]:1002
2. GipherManager  : http://[IP/localhost]:1000
3. GipherRecommendationsSystem : http://[IP/localhost]:1001
4. Eureka Server  : http://[IP/localhost]:9999
5. Zuul Server    : http://[IP/localhost]:9090
6. GipherUI       : http://[IP/localhost]:8080

## Running application
 Follow following steps to run gipher projct
 1. Clone repository
 2. plesae make sure all backend services (mysql and mongo ) are stopped and there are no containers and images in system conflicting with gipher application containers.
 3. navigate to the root directory containing docker compose file
 4. run command `docker-compose up`
 5. navigate to `http://localhost:8080` to see gipher ui frontend.

## Architecture Diagram.

![](Architecture_diagram.PNG)

## Modules:
1. AccountManager - Should be able to manage user accounts
2. GipherUI -
  - User should be able to
    - search GIFs
    - favouriteGIF GIFs
    - should be able to see bookmarked GIFs
  - Application should be a responsive which can smoothly run on mobile devices.
3. GipherManager - Application should be able to store all his
  - bookmarks
  - searches
4. GipherRecommenderSystem - should be able to store all the unique bookmarked gifs from all the users and maintain counter for number of users added a particular gifs into favouriteGIF list.

## Modules Required:
- Spring Boot
- MySQL, MongoDB
- API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

- Building Frontend:
  1. Building Responsive Views:
    - Build a View to show all GIF’s
      - GIF’s - Populating from external api
      - Recommended GIF’s - Populating from GipherRecommenderSystem
      - Build a view to show bookmarked gifs
      - A view to authenticate users
  2. Using Services to populate these data in views
  3. Stitching these views using Routes and Guards
  4. Making the UI Responsive
  5. E2E and Unit tests
  6. Writing CI configuration file
  8. Dockerizing the frontend

- Building the AccountManager
  1. Creating a server in Spring Boot to facilitate registration and login using JWT token and MySQL
  2. Writing Swagger Documentation
  3. Unit Testing
  4. Write CI Configuration
  5. Dockerize the application
  6. Write docker-compose file to build both frontend and backend application
  
- Create an API Gateway which can serve UI and API Request from same host

- Building the GipherManager
  1. Building a server in Spring Boot to facilitate CRUD operation over GIF’s and bookmarked resources stored in MongoDB
  2. Writing Swagger Documentation
  3. Build a Producer for RabbitMQ which
    1. Produces events like what user bookmarked
  4. Write Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose

- Building a GipherRecommenderSystem
  1. Building a Consumer for RabbitMQ
    - i. On a new event generated Update the recommendations in the system. Store the     recommendation list in MongoDB.
    - ii. Maintain list of unique recommended gifs based on what user added into       favouriteGIF and keep counter for number of users added a particular gif into favouriteGIF list.
  2. Build an API to get recommendations
  3. Writing Swagger documentation
  4. Write Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose
  8. Update the API Gateway

- Create Eureka server and make other services as client

- Demonstrate the entire application
