# SolrSysApi

This is a "microservice" application intended to be part of a microservice architecture.


## Development

To start your application in the dev profile, simply run:

    ./mvnw

## Building for production

### Packaging as jar

To build the final jar and optimize the SolrSysApi application for production, run:

    ./mvnw -Pprod clean verify

To ensure everything worked, run:

    java -jar target/*.jar

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    ./mvnw -Pprod,war clean verify

## Testing

To launch your application's tests, run:

    ./mvnw verify


### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

```
Eg. mvn sonar:sonar -Dsonar.projectKey=com.meli.solr.api:solr-sys-api -Dsonar.host.url=http://localhost:9001 -Dsonar.login=3baf8ede15109de122c50071850f19c2b2725d75
```

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

## Using Docker to simplify development

A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

To start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

To dockerize your application and all the services that it depends on, run:

    ./mvnw -Pprod verify jib:dockerBuild
    
And then run:

    docker-compose -f src/main/docker/app.yml up -d
    
For more information about Jib see [site Jib][]    
    
## Deployment

Donwload docker-compose.yml from [public Git repo][]
    
On local, create a folder "meli" and then, run:

	docker-compose up -d    
    
[site Jib]: https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin

[public Git repo]: https://github.com/pbalegno/meli-api/blob/master/src/main/docker/docker-compose.yml
   
