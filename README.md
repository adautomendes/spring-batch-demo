# Spring Batch Demonstration

## Introduction

A simple REST application which interacts with MySQL DB and use a batch job to export the database rows to a CSV file within a batch job.

## REST API

### Create Person

Creates chunks of persons in database to simulate a large amount of data.

```shell
curl --location --request POST 'http://<server_host>:8080/person' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 1000,
    "name": "Adauto Mendes"
}'
```

### Get all Person

Retrives all persons in database.

```shell
curl --location --request GET 'http://<server_host>:8080/person'
```

### Batch Job Trigger

Start the batch job.

```shell
curl --location --request POST 'http://<server_host>:8080/batch/start'
```

Once the batch job is finished a CSV file will be created at `output/data.csv`.

## Installation

Simply run `mvn clean install` under project root folder.

## Dependencies

It requires a MySQL DB to work properly. To make it eaiser run MySQL on docker:

`docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql`

## Running

Simply run `mvn spring-boot:run` under project root folder.