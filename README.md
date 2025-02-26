Agregador de Investimentos com Java, Spring Boot, MySQL e Docker

Este projeto nasceu da minha curiosidade em consumir dados da bolsa de valores e explorar integrações com APIs financeiras, um campo que sempre me interessou. Aproveitei essa oportunidade para aprofundar meus conhecimentos em tecnologias como Java, Spring Boot, Hibernate, MySQL, Docker, JUnit, Mockito e OpenFeign.

A aplicação é uma REST API CRUD, estruturada com Controller, Service e Repository, seguindo as boas práticas do Spring Boot. O banco de dados é gerido através do Hibernate, permitindo o mapeamento automático das entidades.

Principais funcionalidades:

Banco de dados rodando em um ambiente isolado com Docker Compose, facilitando a configuração e a portabilidade da aplicação
Consumo de dados da bolsa de valores via API externa usando OpenFeign
Implementação de operações CRUD no banco de dados MySQL
Relacionamentos entre entidades utilizando @OneToOne, @OneToMany e @ManyToMany
Testes unitários robustos com JUnit 5 e Mockito, aplicando técnicas como argumentCaptor, verify, doReturn, doNothing e doThrow
O uso do Docker foi fundamental para garantir um ambiente de desenvolvimento consistente, simplificando a configuração do MySQL e tornando a aplicação mais escalável.

Este projeto não só me proporcionou uma oportunidade de aplicar boas práticas no desenvolvimento backend, mas também me permitiu explorar integrações com sistemas financeiros de maneira prática.
