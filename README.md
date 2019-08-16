# Spring-Cloud App




### [Spring Cloud](https://spring.io/projects/spring-cloud)

>	There are a wide variety of projects under the umbrella of spring cloud.
>
>	Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems:
>	  * configuration management 
>   * service discovery 
>   * circuit breakers
>   * intelligent
>   * routing
>   * micro-proxy
>   * control bus
>   * one-time tokens
>   * global locks
>   * leadership election
>   * distributed sessions
>   * cluster state


### Advantages

    * new technology and process adoption
        eg. each microservice can be written in a different language

    * dynamic scaling
        eg. during black friday you can scale up your apps

    * faster releases - new features



### Challenges

	1. Bounded context - how do you identify what your microservices should and shouldn't do?
        Empirical analysis -> it's an evolutionary process

	2. Configuration Management?
        Spring-cloud-config

	3. Dynamic scaling [creating new instances]
        dynamic load balancing + distribute the load among the new instances

        tools:
            * Eureka Naming Server  [All instances of all microservices are registered by Eureka]
            * Ribbon                [Client Side Load Balancing]
            * Feign                 [Easier REST client]

	4. Visibility
		Q: how do you identify where the bug is?
		A: centralized log
    
        tools:
            * Zipkin Distributed Tracing
            * Netflix Zul API gateway

  5. Fault Tolerance
        tools:
            * Hystrix


### Centralized Microservice Configuration - Spring Cloud

>	Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system.
>	With the Config Server you have a central place to manage external properties for applications across all environments.
>	The concepts on both client and server map identically to the Spring Environment and PropertySource abstractions, so they fit very well with Spring applications,
>	but can be used with any application running in any language.
>
>	As an application moves through the deployment pipeline from dev to test and into production you can manage the configuration between those environments
>	and be certain that applications have everything they need to run when they migrate.
>
>	The default implementation of the server storage backend uses git so it easily supports labelled versions of configuration environments,
>	as well as being accessible to a wide range of tooling for managing the content. It is easy to add alternative implementations and plug them in with Spring configuration.
>
>	The app connecting to the spring-cloud-config service should rename its application.properties file into bootstrap.properties.


### (Eureka)[https://github.com/Netflix/eureka/wiki]

	Service Discovery: Eureka instances can be registered and clients can discover the instances using Spring-managed beans
	Service Discovery: an embedded Eureka server can be created with declarative Java configuration


### [Spring Netflix Zuul](https://github.com/Netflix/zuul)

> Routing is an integral part of a microservice architecture. 
>	For example / may be mapped to your web application, /api/users is mapped to the user service and /api/shop is mapped to 
>	the shop service. Zuul is a JVM-based router and server-side load balancer from Netflix.

>	Zuul is used at Netflix for:

>		-	Authentication
>		-	Insights
>		-	Stress Testing
>		-	Canary Testing
>		-	Dynamic Routing
>		-	Service Migration
>		-	Load Shedding
>		-	Security
>		-	Static Response handling
>		-	Active/Active traffic management


### [Spring Cloud Sleuth / Zipkin](https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin) [tracing requests]

	Spring Cloud Sleuth implements a distributed tracing solution for Spring Cloud, borrowing heavily from Dapper, Zipkin and HTrace.
	For most users Sleuth should be invisible, and all your interactions with external systems should be instrumented automatically.
	You can capture data simply in logs, or by sending it to a remote collector service.

	check google's https://ai.google/research/pubs/pub36356 / distributed tracing papers

	Zipkin
	https://zipkin.io/pages/quickstart

### [RabbitMQ](https://www.rabbitmq.com/documentation.html)
  > message broker that makes distributed systems development easy. 
	

#### Project Technical Details
##### ports

	Limits Service                      8080, 8081, ...
	Spring Cloud Config Serve           8888
	Currency Exchange Service           8000, 8001, 8002, ..
	Currency Conversion Service         8100, 8101, 8102, ...
	Netflix Eureka Naming Server        8761
	Netflix Zuul API Gateway Server     8765
	Zipkin Distributed Tracing Server   9411
