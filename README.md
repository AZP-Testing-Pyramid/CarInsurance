# CarInsurance-Showcase

## Refactoring Test Pyramid Kata

Welcome to our car insurance application. With our liability insurance, you can individually protect your motor vehicle easily and online. Motor vehicles with a maximum permissible gross weight of up to 3.5 tons are subject to engine-related insurance tax in addition to your premium.

The architecture of the car insurance application consists of an UI, some services and a database.

## Angular App
The UI is implemented with the Angular framework and there are a lot of UI test implemented with cypress (https://www.cypress.io/). The UI code is located in the folder 'car-insurance-app'. For detailed instructions read the README.md there.

## Service Layer
The service layer is implemented with the Spring framework and there no tests at all. The server code is located in the folder 'car-insurance-app'. For detailed instructions read the README.md there.

## Docker

### docker compose

Use docker compose building the server and the client docker images at once. Building will last quite a log time.

`docker compose up -d --build`

Controll the startup of the servers and the current log output with: 

`docker compose logs`

Run the Cypress test 



Stop the application including all docker containers with the command:

`docker compose down`



  e2e:
    container_name: cypress
    build:
      context: car-insurance-app/e2e
      dockerfile: Dockerfile
    depends_on:
      - car-insurance-app
    # command: npx cypress run
    # mount the host directory e2e/cypress and the file e2e/cypress.config.js as
    # volumes within the container
    # this means that:
    #  1. anything that Cypress writes to these folders (e.g., screenshots,
    #     videos) appears also on the Docker host's filesystem
    #  2. any change that the developer applies to Cypress files on the host
    #     machine immediately takes effect within the e2e container (no docker
    #     rebuild required).
    volumes:
      - ./car-insurance-app/e2e/cypress:/app/cypress
      - ./car-insurance-app/e2e/cypress.config.js:/app/cypress.config.js