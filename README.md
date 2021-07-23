# API para ajudar no combate a pandemia

API Rest desenvolvida para ajudar no combate a pandemia, armazenando informações sobre os hospitais e 
seus recursos (pessoas e materiais), possibilitando o intercâmbio entre eles.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:


 Objetivos
  - Adicionar hospitais;
  - Atualizar percentual de ocupação de um hospital;
  - Intercâmbio de recursos;
  - Emissão de relatórios.
  
### Stack utilizada

* Java 11
* Spring Boot Web; JPA; Data;
* Maven
* JUnit
* Swagger 2
* H2 DataBase
* Lombok
* Jasperreports
* Flyway

### Instalação

```sh
$ git clone https://github.com/TiagoPastor/pandemic-combat-api.git
$ cd pandemic-combat-api
$ mvn package
$ cd target
$ java -jar pandemic_combat_api.jar
```

### Sprint Boot Version

```
Pode importar como projeto mavem existente no Spring Tool Suite 4 que foi a versão usada para desenvolver
```

#### Swagger
Desenvolvimento:
```
http://localhost:8080/swagger-ui.html
```
### Arquivo de Collection Json - Usar no Postman

```
  - O arquivo de Collection Json esta na pasta Docs. Pode copiar ele e importar no postman para testar os payloads da api
```


### Versionamento da API

```
O versionamento foi feito pela url, onde a api foi construida como api/v1.

```

###Observação:

```
Ao executar a API é feita uma carga inicial usada para teste.

```

```
Link para acessar o banco H2: 
http://localhost:8080/h2-console
User = sa
Password = ""
host = jdbc:h2:mem:testdb

```


