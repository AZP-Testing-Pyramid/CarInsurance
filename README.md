# CarInsurance-Showcase

## Refactoring Test Pyramid Kata

Welcome to our car insurance application. With our liability insurance, you can individually protect your motor vehicle easily and online. Motor vehicles with a maximum permissible gross weight of up to 3.5 tons are subject to engine-related insurance tax in addition to your premium.

The architecture of the car insurance application consists of the UI (Angular), some services (Java and Spring framework) and a database.

## Angular App
The UI is implemented with the Angular framework and there are a lot of UI test implemented with cypress (https://www.cypress.io/). The UI code is located in the folder 'car-insurance-app'. For detailed instructions read the README.md there.

## Service Layer
The service layer is implemented with the Spring framework and there no tests at all. The server code is located in the folder 'car-insurance-app'. For detailed instructions read the README.md there.

## Docker

### docker compose

Use docker compose building the server and the client docker images at once. Building will last quite a log time.

`docker compose up -d --build`

Control the startup of the servers and the current log output with: 

`docker compose logs`

Stop the application including all docker containers with the command:

`docker compose down`
