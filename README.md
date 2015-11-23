#Processo seletivo para Desenvolvedor Java
Repositório para armazenar as aplicações desenvolvidas para o processo seletivo para desenvolvedor java

#Frameworks e ferramentas utilizadas
O projeto foi desenvolvido utilizando os seguintes tecnologias:
- Java 8
- Spring Boot
- Spring Web Mvc
- Tomcat embutido
- HSQLDB embutido
- Bootstrap
- Thymeleaf
- JavaScript
- JQuery
- JPA
- Maven
- JUNIT
- GSON

#Arquitetura do projeto
- Ambiente de execução embutido - Utilizado o Tomcat embutido, disponibilizado pelo SpringBoot.
- MVC - Projeto desenvolvido respeitando as camadas de  Modelo, Telas e Controladores.
- REST - Esta aplicação disponibiliza um serviço REST para pesquisa de cep. Recebe e responde no formato JSON.
- Banco de Dados embutido - Utilizado o banco de dados HSQLDB em memória.

#Pré requisitos
- Excecutar a aplicação pesquisa-cep para disponibilizar o serviço de pesquisa de ceps
- Java 8
- Maven
	
#Configurações

Os arquivos de propriedades da aplicação se encontram no caminho: src/main/resources

-Application.properties : arquivo de propriedades do Spring
-data.sql: Este arquivo possui a carga de dados inicial da aplicação. Este arquivo é executado automaticamente quando a aplicação for iniciada.

A aplicação está configurada para subir na porta 8082. Caso haja necessidade de alterar esta porta é só alterar o número dela na propriedade server.port do arquivo Application.properties.

#Execução do projeto
ANTES DE EXECUTAR ESTA APLICAÇÃO POR FAVOR EXECUTE A APLICAÇÃO pesquisa-cep PARA O SERVIÇO DE BUSCA DE CEP FICAR DISPONÍVEL PARA ESTA APLICAÇÃO PODER CONSUMI-LO.

Maven: $ mvn clean package spring-boot:run

Utilizando uma IDE: Executar a classe \src\main\java\br\com\tgolopes\EnderecoUsuarioApplication.java
	
Abra o browser e digite http://localhost:8082 esta é a página inicial da aplicação.




