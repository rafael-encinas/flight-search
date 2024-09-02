# Breakable Toy: Flight Search App
The main objective of this project is to develop a fullstack Flight Search App, using React + Typescript for the frontend, Spring Boot + Gradle for the backend, and getting all the information from the Amadeus API.

### Amadeus API
- Flight Offers Search:
https://developers.amadeus.com/self-service/category/flights/api-doc/flight-offers-search
- Airport and City Search:
https://developers.amadeus.com/self-service/category/flights/api-doc/airport-and-city-search/api-reference

## Requirements
The following tools are required to be installed to run these projects:
- Node v20.16
- npm v10.8
- Colima
- Java 21 (openjdk 21.0.4)
- Gradle 8.1
- Docker (CLI) v27.1.2
- Docker Compose 

See each directory for individual requirements.

## Docker compose


Another requirement for this project was to be able to be run using "Docker compose".
In order to be able to do this, you must first build a docker image for both the frontend, and the backend. Once you've done this, you should be able to run both containers running the following command on the project root directory:
> docker compose up

For more details about creating each individual docker images, please refer to each directory's README.md.

## Installing Docker in MacOs

This is beyond the purpose of this README.md, but here are some helpful resources:
- https://dev.to/elliotalexander/how-to-use-docker-without-docker-desktop-on-macos-217m
- https://dev.to/mochafreddo/running-docker-on-macos-without-docker-desktop-64o
- https://stackoverflow.com/questions/60992814/docker-compose-command-not-available-for-mac/77142331#77142331