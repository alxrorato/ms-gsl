# ms-gsl
Sistema de Gestão de Serviços de Logística

## Tecnologias utilizadas
- Java 11
- Spring Boot 2.3.4.RELEASE
- Dependências do Spring Cloud
- Spring Data JPA
- MySQL 8.0
- H2
- Maven

## IDE utilizada
- Spring Tools Suite 4

## Sobre o projeto
Este projeto é uma prova de conceito, desenvolvida somente na camada back-end, para o Sistema de Gestão de Serviços de Logística (GSL), uma arquitetura baseada em microsserviços, com o objetivo de atender as necessidades de uma transportadora nas jornadas de entregas demandadas pelos sites de e-commerce, por exemplo, de supermercados, farmácias, lojas, etc.
Todas as requisições às funcionalidades e também o login, passam pelo microsserviço gsl-api-gateway-zuul, que roteará as requisições aos microsserviços de destino com o auxílio do microsserviço gsl-eureka-server, responsável por tornar detectáveis os endereços dos microsserviços de destino sem que os aplicativos clientes possuam o endereço deles codificados. Os demais microsserviços desta implementação são:
- gsl-entrega: implementa as funcionalidades referente a uma jornada de entrega, com ênfase às que são referentes aos casos de uso previstos para esta POC:
  - UC01 - Estimar valor do frete - Ator: cliente
  - UC02 - Solicitar entrega com acionamento de transportadora parceira - Atores: cliente e transportadora parceira
  - UC03 - Emissão do CT-e (Conhecimento de Transporte Eletrônico) - Ator: colaborador da transportadora
- gsl-cliente: implementa o CRUD para as informações cadastrais de clientes. Ao subir o este microsserviço, automaticamente já são inseridos alguns clientes no banco de dados                  para teste.
- gsl-oauth: microsserviço autorizador que implementa o protocolo OAuth2. Recebe as credenciais de login no sistema, repassadas pelo API Gateway; acessa o microsserviço de                    Usuários e Roles (gsl-user) para validar o usuário que consta nas credencias recebidas e, se válido, devolve um token de autorização para que o usuário possa então              acessar as funcionalidades dos demais microsserviços cujo perfil de acesso exigido seja o mesmo.
- gsl-user: microsserviço responsável por manter o cadastro de usuários e suas roles, que identificam seus perfis.
- gsl-config-server: microsserviço que fornece aos demais microssesrviços, acesso ao repositório ms-gsl-configs no Github, para obtenção de dados de configurações presentes em     arquivos properties deste repositório.
- gsl-parceira: microsserviço fora do contexto do Spring Cloud. Representa um sistema externo de uma transportadora parceira, e retorna dados mockados, quando acionado pela                     funcionalidade de solicitação de entrega (UC01).
- gsl-sfc: microsserviço fora do contexto do Spring Cloud. Representa o sistema legado de faturamento e cobrança (SFC), que foi implementado para receber os dados de um CT-e       emitido. É acionado pela funcionalidade de emissão do CT-e (UC03)
- gsl-google-services-mock: microsserviço que não faz parte do contexto desta POC. Representa um sistema de geo-localização, retornando dados mockados de latitude e longitute. Foi implementado pensando-se numa evolução posterior desta POC, para atender à funcionalidade de consulta do andamento de uma entrega, que também faz parte de uma jornada de entrega, e não está no escopo desta POC. Portanto, não há necessidade de subir este microsserviço para os testes dos casos de uso escopo desta POC já citados.

A figura abaixo ilustra esta implementação e contém comentários explicando os principais módulos:

![POC](https://github.com/alxrorato/ms-gsl-images/blob/main/POC-estrutura-implementacao.png)

Esta POC teve como objetivo a implementação de 3 casos de uso:
- UC01 - Estimar valor do frete - Ator: cliente
- UC02 - Solicitar entrega com acionamento de transportadora parceira - Atores: cliente e transportadora parceira
- UC03 - Emissão do CT-e (Conhecimento de Transporte Eletrônico) - Ator: colaborador da transportadora

## Definir qual banco de dados será utilizado:
Nos microsserviços gsl-entrega, gsl-cliente e gsl-user, acessar no projeto o arquivo bootstrap.properties, abaixo da pasta resources, e alterar o parâmetro "spring.profiles.active" para umas das duas strings abaixo:
- prod : se desejar utilizar o MySql
- test : se desejar utilizar o H2.

## Como subir localmente os microsserviços pela IDE 
Conforme a figura, os primeiros microsserviços a subir devem ser o gsl-config-server e o gsl-eureka-server. E em seguida os demais, em qualquer ordem
![STS](https://github.com/alxrorato/ms-gsl-images/blob/main/STS-img.png)

## Como executar
Os testes devem ser realizados pela ferramenta Postman e para isto deve ser importada a collection ms-gsl.postman_collection.json
que se encontra no diretório raiz do projeto.

Todas as requisições às funcionalidades, inclusive o login, passam pelo API GatewayA autenticação e autorização login foi implementado no microsserviço gsl-oauthythcasos 

Incluir usuário e senha nas variáveis de ambiente username e password, respectivamente, conforme imagem abaixo:


## Usuários, senhas e roles para testes:

jose@gmail.com - 123456	- CLIENTE
lucas@gmail.com	- 123457 - COLABORADOR
cristina@gmail.com - 123458	- FORNECEDOR
alexandre@gmail.com	- 123459- ADMIN

## Autor
Alexandre Rorato Carneiro

https://www.linkedin.com/in/alexandre-rorato-carneiro-a28a9b2b/
