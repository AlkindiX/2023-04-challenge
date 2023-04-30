# Challenge

This is a project that utilizes Maven as the build tool. This README file provides an overview of how to build, deploy, and develop the project.

## Build

To build the project, follow these steps:

1. Ensure that Maven is installed on your machine.
2. Clone the project repository to your local machine.
3. Navigate to the project root directory.
4. Open a terminal and run the following command:
    In Windows. Open POSH and run the following command:
    ```PowerShell
    ./mvnw.cmd clean install
    ```
    In Linux. Open a terminal and run the following command:
    ```bash
      ./mvnw clean install
    ```

    This command will download all the project dependencies, compile the source code, and generate the output artifact.
    The artifact will be located in the `target` directory. The compiler output is in jar format. The name of the artifact will be in the following format:

    ```
      <artifactId>-<version>.jar
      
    ```
    For example `target/challenge-0.0.1-SNAPSHOT.jar`.

5. The generated output artifact will be located in the `target` directory.

## Run

To run the project locally. Use the following command

In Windows. Open POSH and run the following command:
```PowerShell
./mvnw.cmd spring-boot:run
```

In Linux. Open a terminal and run the following command:
```bash
  ./mvnw spring-boot:run
```

Any modification on the source file will trigger the devtools that is installed
on the pom.xml and will hot-reload the application.

The same thing can be applied with docker-compose. The following command will
build and run the project with hot-reload enabled. Make sure docker and docker-compose
are both installed in your local machine.

```bash
  docker-compose up devel
```

## Deploy Using Docker

To run the project using Docker Compose, follow these steps:

1. Ensure that Docker and Docker Compose are installed on your machine.
2. Run the following command
  ```bash
    docker-compose pull
    docker compose up maven-build
    docker compose up prod
  ```

## How to Develop

To develop the project, follow these steps:

1. Ensure that you have the following tools installed on your machine:
   - JDK (Java Development Kit) version 17 LTS or later
   - Maven 3 or later
   - An IDE (Integrated Development Environment) of your choice (e.g. Eclipse, IntelliJ IDEA, NetBeans).
    my current choice is VSCode. To use in VSCode, add the following json file in `.vscode/settings.json`
    for better IDE experience
      ```json
      {
        "java.compile.nullAnalysis.mode": "automatic",
        "editor.formatOnSave": true,
        "editor.formatOnSaveMode": "modifications",
        "java.format.settings.profile": "java-edicom-eclipse-formatter-v2.xmal",
        "editor.codeActionsOnSave": {
          "source.organizeImports": true
        },
        "java.eclipse.downloadSources": true,
        "java.maven.downloadSources": true,
        "java.configuration.updateBuildConfiguration": "interactive"
      }

      ```
      To test endpoints. Install the following extension in VSCode
      ```
        REST Client
      ```

## Available endpoints

```http
### Towers endpoint
GET http://localhost:8080/challenge/towers

### Health check endpoint
GET http://localhost:8080/health-check
```

Please refer to the following [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) for more information

## Bonus

The following endpoints are available for the bonus part of the challenge

```http
### I would like to search for a tower using geo coordinates and radius. To do that run the following
GET http://localhost:8080/challenge/towers?latitude=37.469610248472584&longitude=-77.25050725083281&radius=100
```

### Note

You can use any of the following params to query any data listed in [Tower.java](src\main\java\work\alkindix\byanat\challenge\resolvers\Tower.java)

```Java
static String[] FIELDS = {
    "tower_id",
    "operator",
    "address",
    "height",
    "tower_type",
    "latitude",
    "longitude",
    "technology"
  };

```

## Conclusion

This README file has provided an overview of how to build, deploy, and develop the project. For more information, refer to the project documentation or contact the project team.