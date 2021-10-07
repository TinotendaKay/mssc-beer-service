[![CircleCI](https://circleci.com/gh/TinotendaKay/mssc-beer-service/tree/main.svg?style=svg)](https://circleci.com/gh/TinotendaKay/mssc-beer-service/tree/main)

# MSSC Beer Service

Spring Boot Microservice example


|  Service Name |    Port  |
| ------------- | -------- |
| Brewery Beer Service| 8080 |
| Brewery Beer Oder Service | 8081 |
| Brewery Beer Inventory Service| 8082 |

## Active MQ Server

Start docker service

docker run -it --rm -p 8161:8161 -p 61616:61616  vromero/activemq-artemis

username and password of artemis / simetraehcapa