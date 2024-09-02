# Flight Search Frontend
## Requirements
The following tools are required to be installed to run this project:
- Node v20.16
- npm v10.8
- Colima
- Docker (CLI) v27.1.2
- Docker Compose 
## Enviroment variables
This project uses an environment variable called "VITE_REACT_APP_API_AIRPORTS_URL" for the backend API url, so in order for the project to work correctly please make sure to create a new ".env" file in this directory, as shown here:

![.env file location](./public/env_file_location.png?raw=true)

It should contain a single variable called "VITE_REACT_APP_API_AIRPORTS_URL" with the API URL:
![API endpoint](./public/api_endpoint.png?raw=true)

## Docker
Once you have environment variable for the API URL is setup, you may now run the project as Docker container.

1. Start Colima:
> colima start

2.  Create an image build:
> docker build . -t "flight-search-front"

3. run it using Docker compose:
> docker compose up

### Changing Dockerfile
If you need to change anything from "Dockerfile", please make sure to replicate this change on the "Dockerfile.dev" file as well, since this is the one used when running both frontend and backend as a single Docker compose.

## Project
To install all the necessary dependencies, run the following command in this directory:
> npm install

To run the project:
> npm run start

To run the tests:
> npm run test

## Installing Docker in MacOs

This is beyond the purpose of this README.md, but here are some helpful resources:
- https://dev.to/elliotalexander/how-to-use-docker-without-docker-desktop-on-macos-217m
- https://dev.to/mochafreddo/running-docker-on-macos-without-docker-desktop-64o
- https://stackoverflow.com/questions/60992814/docker-compose-command-not-available-for-mac/77142331#77142331