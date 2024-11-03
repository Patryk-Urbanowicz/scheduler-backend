# Scheduler-backend

### Running a app
To run this app you need to have docker installed and configured. App deploys MySQL docker container.
For authorization to work you need to generate private and public key - their localization is configurable via application.properties.
Default database is currently being generated via CommandLineRunner and credentials can be configured in compose.yaml file.