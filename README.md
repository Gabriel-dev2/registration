# Rest Api for Registration of People

## Overview

**This api uses the Spring Fox Swagger Documentation, to access you just need to make a get request to:**

    Ex: http://localhost:8080/swagger-ui.html#/

## Prerequisites
- Java 11
- Maven 3.6.3

## Install

**To run the build of the application you can use**

    ./mvnw clean install

**Or if you don't want to run the unit tests just make the build directly using**

    ./mvnw clean install -DskipTests


## Instructions to run this API

**Before you run this application make sure to take a look at the application.properties, if you want to use a external mongodb url you may need to declare a env variable MONGO_DB_URI:**

    export MONGODB=mongodb://root:root@localhost:27017

**To execute this application you can do this directly on the command line with the following command:**

    mvn spring-boot:run

**If you are running this using VSCode please install the following plugins:**

- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

- [Code Runner](https://marketplace.visualstudio.com/items?itemName=formulahendry.code-runner)

    **Configure the launch Template**
        ```json

        {
            "configurations": [
                {
                    "type": "java",
                    "name": "Launch RegistrationApplication",
                    "request": "launch",
                    "mainClass": "com.person.registration.RegistrationApplication",
                    "projectName": "registration",
                    "env": {
                        "MONGO_DB_URI": "mongodb+srv://<username>:<password>@yourCluster
                    }
                }
            ]
        }
        ```

## Authors

* **Gabriel Lucas** - *Initial work* - [Gabriel](mailto:gabriel23costalima@outlook.com)

