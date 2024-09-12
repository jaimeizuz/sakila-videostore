# Kogito and Infrastructure services

Compilation order (command to trigger: mvn clean install), only required for starting the local app without docker-compose:
1. sakila-videostore-jpa-services
2. sakila-videostore-rest-services-dto
3. sakila-videostore-rest-services-client
4. sakila-videostore-rest-services-server
5. sakila-videostore-processes-custom-user-task-lifecycle
6. sakila-videostore-processes-servicetasks
   
There are two execution options:
1. Execute the kogito app in dev mode. It will start the server in localhost:8080, where you can access the management console in http://localhost:8080/q/dev-ui/org.jbpm.jbpm-quarkus-devui/process-instances. This option doesn't include logs&traces
    Command: mvn clean quarkus:dev -Pdevelopment (execute it in the sakila-videostore-processes-workflows folder)

2. Start the docker-compose. It allows you to start the full stack, including logs&traces
    Command: docker-compose --profile business-services --profile kogito-database --profile business-database --profile monitoring --profile kogito-services --profile kogito-consoles --profile messaging-redpanda --profile workflows --profile elk up -d

    2.1. Business services
    docker-compose --profile business-services --profile business-database up -d

    2.2. Monitoring&Events stack
    docker-compose --profile monitoring --profile messaging-redpanda --profile elk up -d

To access the docker-compose services:
1. Jaeger UI (traces): http://localhost:16686/
2. Kibana dashboards: http://localhost:5601/
3. Kafka events console (based on Redpanda UI): http://localhost:9001/
4. Kogito Management Console: http://localhost:8280/ (user:jdoe, password:jdoe)
5. Kogito Tasks Console: http://localhost:8380/ (user:jdoe, password:jdoe)
6. PostgreSQL database: postgresql://postgres:5432/kogito (user: kogito-user, password: kogito-pass)

To start a process instance, just execute this HTTP request:
    curl -X POST -H 'Content-Type:application/json' http://localhost:8080/UserTaskProcess -d '{}'