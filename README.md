# Sistema de Gestão de Serviços de Logística (GSL)

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
Este projeto é uma prova de conceito que faz parte do meu TCC da pós-graduação em Arquitetura de Software Distribuído na PUC-Minas, desenvolvida somente na camada back-end, para uma arquitetura baseada em microsserviços, com o objetivo de atender as necessidades de uma transportadora nas jornadas de entregas demandadas pelos sites de e-commerce, por exemplo, de supermercados, farmácias, lojas, etc.
Todas as requisições às funcionalidades e também o login, passam pelo microsserviço __gsl-api-gateway-zuul__, que roteará as requisições aos microsserviços de destino com o auxílio do microsserviço __gsl-eureka-server__, responsável por tornar detectáveis os endereços dos microsserviços de destino sem que os aplicativos clientes possuam o endereço deles codificados. Os demais microsserviços desta implementação são:
- __gsl-entrega__: implementa as funcionalidades referente a uma jornada de entrega, com ênfase às que são referentes aos casos de uso previstos para esta POC:
  - __UC01 - Estimar valor do frete__ - Ator: cliente
  - __UC02 - Solicitar entrega com acionamento de transportadora parceira__ - Atores: cliente e transportadora parceira
  - __UC03 - Emissão do CT-e (Conhecimento de Transporte Eletrônico)__ - Ator: colaborador da transportadora
- __gsl-cliente__: implementa o CRUD para as informações cadastrais de clientes. Ao subir o este microsserviço, automaticamente já são inseridos alguns clientes no banco de dados                  para teste.
- __gsl-oauth__: microsserviço autorizador que implementa o protocolo OAuth2. Recebe as credenciais de login no sistema, repassadas pelo API Gateway; acessa o microsserviço de                    Usuários e Roles (gsl-user) para validar o usuário que consta nas credencias recebidas e, se válido, devolve um token de autorização para que o usuário possa então              acessar as funcionalidades dos demais microsserviços cujo perfil de acesso exigido seja o mesmo.
- __gsl-user__: microsserviço responsável por manter o cadastro de usuários e suas roles, que identificam seus perfis.
- __gsl-config-server__: microsserviço que fornece aos demais microssesrviços, acesso ao repositório ms-gsl-configs no Github, para obtenção de dados de configurações presentes em     arquivos properties deste repositório.
- __gsl-parceira__: microsserviço fora do contexto do Spring Cloud. Representa um sistema externo de uma transportadora parceira, e retorna dados mockados, quando acionado pela                     funcionalidade de solicitação de entrega (UC01).
- __gsl-sfc__: microsserviço fora do contexto do Spring Cloud. Representa o sistema legado de faturamento e cobrança (SFC), que foi implementado para receber os dados de um CT-e       emitido. É acionado pela funcionalidade de emissão do CT-e (UC03)
- __gsl-google-services-mock__: microsserviço que não faz parte do contexto desta POC e por isto não consta no desenho abaixo. Representa um sistema de geo-localização, retornando     dados mockados de latitude e longitute. Foi implementado pensando-se numa evolução posterior desta POC, para atender à funcionalidade de consulta do andamento de uma entrega,   que também faz parte de uma jornada de entrega, e não está no escopo desta POC, apesar de ter sido implementada no gsl-entrega. Portanto, não há necessidade de subir este       microsserviço para os testes dos casos de uso escopo desta POC já citados.

A figura abaixo ilustra esta implementação e contém comentários explicando os principais módulos:

![POC](https://github.com/alxrorato/ms-gsl-images/blob/main/POC-estrutura-implementacao.png)

Esta POC teve como objetivo a implementação dos 3 casos de uso já citados e validar os requisitos funcionais de desempenho, segurança, interoperabilidade e manutenibilidade.

## Definir qual banco de dados será utilizado:
Nos microsserviços gsl-entrega, gsl-cliente e gsl-user, acessar no projeto o arquivo bootstrap.properties, abaixo da pasta resources, e alterar o parâmetro "spring.profiles.active" para umas das duas strings abaixo:
- **_prod_** : se desejar utilizar o MySql
- **_test_** : se desejar utilizar o H2.

## Como subir localmente os microsserviços pela IDE 
Conforme a figura, os primeiros microsserviços a subir devem ser o gsl-config-server e o gsl-eureka-server. E em seguida os demais, em qualquer ordem.
![STS](https://github.com/alxrorato/ms-gsl-images/blob/main/STS-img.png)

## Como executar os casos de uso
Os testes devem ser realizados pela ferramenta __Postman__ e para isto deve ser importada a collection **_ms-gsl.postman_collection.json_**
que se encontra no diretório raiz do projeto. As funcionalidades configuradas nele já estão com suas abas Body preenchidas (se aplicável) e na aba Authorization o token é automaticamente informado porque faz referência a variável de ambiente {{token}}, cujo conteúdo é sempre o token gerado no último login. Nas URLs, são referenciadas a variável de ambiente {{api-gateway}}, que é o endereço de microsserviço gsl-api-gateway-zuul; e quando aplicável, alguma variável referenciando um parâmetro, por exemplo, {{codigo-solicitacao}}

__1)__ Antes, incluir usuário e senha para login nas variáveis de ambiente username e password, respectivamente, conforme imagem abaixo:

   __Obs.:__ no tópico seguinte deste documento há alguns usuários carregados no banco no momento da inicialização do microsserviço gsl-user, com suas respectivas roles. Para os              casos de uso UC01 e UC02, o usuário logado dever possuir a role CLIENTE ou ADMIN; e para o caso de uso UC03, a role COLABORADOR ou ADMIN.
   
![USER_PASS](https://github.com/alxrorato/ms-gsl-images/blob/main/Postman-user_pass.png)

__2)__ Efetuar o login e geração do token de autorização:

![LOGIN_TOKEN](https://github.com/alxrorato/ms-gsl-images/blob/main/Postman-login-token.png)

__3)__ Executar caso de uso UC01:

![UC01](https://github.com/alxrorato/ms-gsl-images/blob/main/Postman-UC01.png)

__4)__ Executar caso de uso UC02:

![UC02](https://github.com/alxrorato/ms-gsl-images/blob/main/Postman-UC02.png)

__5)__ Executar caso de uso UC03:

![UC03](https://github.com/alxrorato/ms-gsl-images/blob/main/Postman-UC03.png)

## Usuários para testes:

Seguem usuários seguidos de suas respectivas senhas e roles

- jose@gmail.com - 123456	- CLIENTE
- lucas@gmail.com	- 123457 - COLABORADOR
- cristina@gmail.com - 123458	- FORNECEDOR
- alexandre@gmail.com	- 123459- ADMIN

## Documento do TCC: 
https://github.com/alxrorato/ms-gsl-images/blob/main/TCC_Alexandre-Rorato-Carneiro.pdf

## Autor
Alexandre Rorato Carneiro

https://www.linkedin.com/in/alexandre-rorato-carneiro-a28a9b2b/
