# ms-gsl
Gestão de Serviços de Logística - microsserviços

## Tecnologias utilizadas
- Java 11
- Spring Boot 2.3.4.RELEASE
- Spring Cloud
- Spring Data JPA
- MySQL 8.0
- H2
- Maven

## IDE utilizada
- Spring Tools Suite 4

## Sobre o projeto
Este projeto é uma prova de conceito para o Sistema de Gestão de Serviços de Logística (GSL), uma arquitetura baseada em microsserviços, com o objetivo de atender as necessidades de uma transportadora nas jornadas de entregas demandadas pelos sites de e-commerce, por exemplo, de supermercados, farmácias, lojas, etc.
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

Incluir usuário e senha nas variáveis de ambiente username e password, respectivamente, conforme imagem abaixo:


## Usuários, senhas e roles para testes:

jose@gmail.com - 123456	- CLIENTE
lucas@gmail.com	- 123457 - COLABORADOR
cristina@gmail.com - 123458	- FORNECEDOR
alexandre@gmail.com	- 123459- ADMIN

## Autor
Alexandre Rorato Carneiro

https://www.linkedin.com/in/alexandre-rorato-carneiro-a28a9b2b/
