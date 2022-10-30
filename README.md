# Sample Cloud Stream Application
Sample application using kafka binders to produce/consume message to/from kafka topics

## Table of contents
- [Message schema](#message-schema)
- [Services](#services)
- [Sidenotes](#sidenotes)

## Message schema
Message key & value schemas are defined using avro format. Both of them are being published to schema registry by producer whereas consumer relies on them while reading the incoming message

`processing-key.avsc`:
```json
{
  "name": "ProcessingKey",
  "type": "record",
  "namespace": "com.kafka.processing.avro.model",
  "doc": "Sample object used as a kafka message key",
  "fields": [
    {
      "name": "operator",
      "type": "string"
    },
    {
      "name": "client",
      "type": "string"
    }
  ]
}
```

`processing-value.avsc`:
```json
{
  "name": "ProcessingValue",
  "type": "record",
  "namespace": "com.kafka.processing.avro.model",
  "doc": "Sample object used as a kafka message value",
  "fields": [
    {
      "name": "processingTimeMillis",
      "type": "long"
    }
  ]
}
```

## Services
| Service         | Purpose                                    | Url                   |
|-----------------|--------------------------------------------|-----------------------|
| zookeeper       | Kafka maintaining                          | _Isolated usage_      |
| kafka           | Message broker                             | http://localhost:9092 |
| schema-registry | Storing and validating avro message schema | http://localhost:8081 |
| redpanda        | Web UI                                     | http://localhost:8080 |

## Sidenotes
Local running requires such tech stack:
- `gradle 7.4`
- `java 17.0.2`
- `docker 4.12.0`
- `docker-compose 1.29.2`

```shell
docker-compose up

docker exec -it kafka bash

kafka-topics --bootstrap-server localhost:9092 --list

kafka-topics --bootstrap-server localhost:9092 --create \
    --replication-factor 1 \
    --partitions 1 \
    --config cleanup.policy=compact \
    --topic message.processing
```
