# ms-gsl
Gestão de Serviços de Logística - microsserviços

# Instalação da aplicação sem uma IDE

- Abrir uma janela do cmd e fazer o build de todos os projetos usando o pom.xml do nível raiz com o comando abaixo: 

mvn –Dmaven.test.skip=true clean install

- Instalar os microsserviços executando os arquivos BAT abaixo nesta ordem:

1) init-gls-config-server.bat
Inicializa o serviço gsl-config-server na porta 8888. 
Obs.: aguardar este serviço subir antes de subir o próximo.

2) init-gsl-eureka-server.bat
Inicializa o serviço gsl-eureka-server na porta 8761.
Obs.: aguardar este serviço subir antes de subir od demais.

3) init-demais.bat 

- Inicializa os seguintes microsserviços em portas aleatórias:
gls-user, gsl-oauth, gsl-cliente e gsl-entrega
Caso deseje utilizar uma porta fixa, basta alterar o bat e indicar uma porta específica em "-Dserver.port".
Ex.: start java -Dserver.port=8080 -jar gsl-entrega\target\gsl-entrega-0.0.1-SNAPSHOT.jar

- Inicializa o serviço legado gsl-sfc na porta fixa 8500, que está configurada no arquivo application.properties, o qual pode ser alterado
caso deseje utilizar outra porta.

- Inicializa os serviços externos gsl-parceira e gsl-google-services-mock nas portas 7001 e 7002, respectivamente, e que podem ser alteradas
indicando as novas portas nos respectivos arquivos application.properties desses serviços.
