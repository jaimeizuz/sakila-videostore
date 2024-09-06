@See https://quarkus.io/blog/quarkus-mutual-tls/
@See https://blog.devolutions.net/2020/07/tutorial-how-to-generate-secure-self-signed-server-and-client-certificates-with-openssl/
@See https://dev.to/felipewind/quarkus-mutual-tls-demo-4jnn#test---server-with-certificate-and-client-without-truststore-configured
@See https://medium.com/expedia-group-tech/how-to-import-public-certificates-into-javas-truststore-from-a-browser-a35e49a806dc

mvn clean `
 '-Dquarkus.http.cors=true' `
 '-Dquarkus.http.cors.origins=*' `
 '-Dquarkus.datasource.db-kind=mysql' `
 '-Dquarkus.datasource.username=sakilauser' `
 '-Dquarkus.datasource.password=Password@1!' `
 '-Dquarkus.datasource.jdbc.url=jdbc:mysql://localhost:3307/sakila' `
 '-Dquarkus.swagger-ui.always-include=true' `
 '-Dquarkus.http.ssl.client-auth=none' `
 '-Dquarkus.http.insecure-requests=disabled' `
 '-Dquarkus.http.ssl.certificate.files=C:/Users/Jaime/Documents/development/code/kie/kogito/custom-projects/sakila-video-store/sakila-security/sakila-videostore-rest-services-server-certificates/server/server.crt' `
 '-Dquarkus.http.ssl.certificate.key-files=C:/Users/Jaime/Documents/development/code/kie/kogito/custom-projects/sakila-video-store/sakila-security/sakila-videostore-rest-services-server-certificates/server/server.key' `
 '-Dquarkus.http.ssl.port=8443' `
 quarkus:dev



curl -vvv -k https://localhost:8443/countries

C:\Users\Jaime\Documents\development\tools\curl-8.8.0_3-win64-mingw\bin\curl.exe -vvv -k `
     --cacert C:\Users\Jaime\Documents\development\code\kie\kogito\custom-projects\sakila-video-store\sakila-security\sakila-videostore-certificate-authority\ca.crt `
     --cert C:\Users\Jaime\Documents\development\code\kie\kogito\custom-projects\sakila-video-store\sakila-security\sakila-videostore-rest-services-server-certificates\client\client1.crt:Password@21! `
 --key C:\Users\Jaime\Documents\development\code\kie\kogito\custom-projects\sakila-video-store\sakila-security\sakila-videostore-rest-services-server-certificates\client\client1.key `
https://localhost:8443/countries