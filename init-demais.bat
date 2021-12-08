rem Faz o build de todos os projetos usando o pom.xml do nível raiz: 

rem mvn –Dmaven.test.skip=true clean install
rem timeout 5

rem Executa cada microsserviço no seu respectivo folder:

start java -jar gsl-api-gateway-zuul\target\gsl-api-gateway-zuul-0.0.1-SNAPSHOT.jar
start java -jar gsl-cliente\target\gsl-cliente-0.0.1-SNAPSHOT.jar
start java -jar gsl-entrega\target\gsl-entrega-0.0.1-SNAPSHOT.jar
start java -jar gsl-oauth\target\gsl-oauth-0.0.1-SNAPSHOT.jar
start java -jar gsl-parceira\target\gsl-parceira-0.0.1-SNAPSHOT.jar
start java -jar gsl-sfc\target\gsl-sfc-0.0.1-SNAPSHOT.jar
start java -jar gsl-google-services-mock\target\gsl-google-services-mock-0.0.1-SNAPSHOT.jar
start java -jar gsl-user\target\gsl-user-0.0.1-SNAPSHOT.jar
