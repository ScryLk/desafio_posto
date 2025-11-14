# Sistema de Gerenciamento de Posto de Combustível

## Sobre o Projeto

Este projeto é um **fork** do repositório original: [merito-es/vaga-junior](https://github.com/merito-es/vaga-junior)

O fork foi utilizado para desenvolver a solução do desafio proposto, permitindo manter o histórico completo de commits e evolução do código.

---

Sistema web para gerenciar combustíveis, bombas e abastecimentos com base no desafio solicitado.

## Tecnologias

- Java 11 + Jakarta EE 10
- MySQL 8.0
- Apache Tomcat 10.1
- HTML/CSS/JavaScript

## Como Executar

1. Criar o banco de dados MySQL
2. Configurar credenciais em `src/main/resources/db.properties`
3. Compilar
4. Implantar o WAR no Tomcat
5. Acessar: `http://localhost:8080`

## Funcionalidades

- CRUD de combustíveis
- CRUD de bombas
- Registro e consulta de abastecimentos
