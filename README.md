## Summit 2021 Jornada Colaborativa Microsserviços | Spring Data JPA - Multitenant

## Instalação JDK 11
* https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

## Extensões do VS Code:
As seguintes extensões do Visual Studio Code deverão estar instaladas:
* `Java Extension Pack (Microsoft)`
* `Spring Boot Extension Pack (Pivotal)`
* `Lombok Annotations Support for VS Code (Gabriel Basilio Brito)`


## Instalação do Docker
* `Docker instalado e configurado`
* `Criar os seguintes containers Docker:`

```sh
docker run --name oracle-xe-01 -d -p 1521:1521 -p 8080:8080 enomoto/oracle-xe-11g-r2
docker run --name oracle-xe-02 -d -p 1522:1521 -p 8081:8080 enomoto/oracle-xe-11g-r2
```   

## Instalação driver JDBC Oracle
* `Fazer o download e instalação do seguinte driver JDBC - ojdbc7.jar`
* `mvn install:install-file -Dfile="ojdbc7.jar" -DgroupId="com.oracle" -DartifactId="ojdbc7" -Dversion="12.1.0" -Dpackaging=jar`

## Instalação do Maven

* https://maven.apache.org/download.cgi -> Instalar a versão 3.6.3</br>
* Caso esteja utilizando Windows vc poderá fazer a instalação do Maven via Chocolatey (Package Manager para Windows):</br>
•	https://chocolatey.org/</br>
•	https://chocolatey.org/packages/maven</br>


## Lombok
```
https://projectlombok.org/
https://projectlombok.org/features/Data
https://projectlombok.org/features/GetterSetter
https://www.projectlombok.org/features/Builder
https://projectlombok.org/features/constructor

```

## Principais Anotações do Spring
```
@Configuration
É uma annotation que indica que determinada classe possui métodos que expõe novos beans.

@Controller
Associada com classes que possuem métodos que processam requests numa aplicação web.

@Repository
Anotação que serve para definir uma classe como pertencente à camada de persistência.

@Service
Anotação que serve para definir uma classe como pertencente à camada de Serviço da aplicação.

@Autowired
A anotação @ Autowired fornece controle sobre onde e como a ligação entre os beans deve ser realizada. Pode ser usado para em métodos setter, no construtor, em uma propriedade ou métodos com nomes arbitrários e / ou vários argumentos.
```

## Spring Boot - Interceptor
```
You can use the Interceptor in Spring Boot to perform operations under the following situations −

Before sending the request to the controller
Before sending the response to the client

The following are the three methods you should know about while working on Interceptors −

preHandle() method − This is used to perform operations before sending the request to the controller. This method should return true to return the response to the client.

postHandle() method − This is used to perform operations before sending the response to the client.

afterCompletion() method − This is used to perform operations after completing the request and response.

https://www.tutorialspoint.com/spring_boot/spring_boot_interceptor.htm
```

## ThreadLocal API
```
https://www.baeldung.com/java-threadlocal
```

## AbstractRoutingDataSource
```
https://fizzylogic.nl/2016/01/24/make-your-spring-boot-application-multi-tenant-aware-in-2-steps/
https://github.com/wmeints/spring-multi-tenant-demo
https://www.baeldung.com/spring-abstract-routing-data-source
https://github.com/Cepr0/sb-multitenant-db-demo
```




