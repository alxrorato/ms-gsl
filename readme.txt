1) Faz o build de todos os projetos usando o pom.xml do nível raiz: 

mvn –Dmaven.test.skip=true clean install

2) Executa cada microsserviço no seu respectivo folder:

java -jar gsl-config-server\target\gsl-config-server-0.0.1-SNAPSHOT.jar
java -jar gsl-eureka-server\target\gsl-eureka-server-0.0.1-SNAPSHOT.jar
java -jar gsl-api-gateway-zuul\target\gsl-api-gateway-zuul-0.0.1-SNAPSHOT.jar
java -jar gsl-cliente\target\gsl-cliente-0.0.1-SNAPSHOT.jar
java -jar gsl-entrega\target\gsl-entrega-0.0.1-SNAPSHOT.jar
java -jar gsl-oauth\target\gsl-oauth-0.0.1-SNAPSHOT.jar
java -jar gsl-parceira\target\gsl-parceira-0.0.1-SNAPSHOT.jar
java -jar gsl-sfc\target\gsl-sfc-0.0.1-SNAPSHOT.jar
java -jar gsl-google-services-mock\target\gsl-google-services-mock-0.0.1-SNAPSHOT.jar
java -jar gsl-user\target\gsl-user-0.0.1-SNAPSHOT.jar

