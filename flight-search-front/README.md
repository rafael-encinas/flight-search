# Flight Search Frontend

## Enviroment variables
This project uses an environment variable called "VITE_REACT_APP_API_AIRPORTS_URL" for the backend API url, so in order for the project to work correctly please make sure to create a new ".env" file in this directory, as shown here:

![.env file location](./public/env_file_location.png?raw=true)

It should contain a single variable called "VITE_REACT_APP_API_AIRPORTS_URL" with the API URL:
![API endpoint](./public/api_endpoint.png?raw=true)

## Docker
Once you have environment variable for the API URL is setup, you may now run the project as Docker container.

To create an image build, please run the following command:
> docker build . -t "flight-search-front"

And to run it using Docker compose, run the following command:
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

components