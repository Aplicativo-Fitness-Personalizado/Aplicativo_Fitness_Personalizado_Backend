# Aplicativo de Fitness Personalizado

Projeto desenvolvido como parte do **Desafio 2 do Projeto Integrador (Backend)**, com foco na criação de uma aplicação fitness de treinos personalizados.

<p align="center">
 <a href="#descrição-do-projeto">Descrição</a> •
 <a href="#problema-que-o-projeto-visa-resolver">Problema</a> •
 <a href="#entidade-e-atributos">Entidades</a> •
 <a href="#funcionalidades-do-crud">Funcionalidades</a> •
 <a href="#tecnologias-utilizadas">Tecnologias</a> • 
 <a href="#teste">Testes</a> •
 <a href="#integrantes-do-grupo">Integrantes</a>
</p>

## 📖 Descrição do Projeto

A aplicação de Fitness se trata de um sistema de cadastro e autenticação de usuários voltado para o acompanhamento da saúde física e controle de treinos personalizados. O sistema permite calcular o IMC (Índice de Massa Corporal) dos usuários, além de oferecer funcionalidades CRUD completas.

## ❗ Problema que o projeto visa resolver

Muitas pessoas iniciam suas rotinas de treino sem um controle real de sua saúde corporal. O projeto busca resolver a falta de organização e personalização no acompanhamento fitness, possibilitando que usuários cadastrem seus dados, acessem informações de saúde (como o IMC) e mantenham um histórico organizado para seu progresso.

## 🗃️ Entidades e Atributos

### Banco de dados (`db_appacademia`)

### Usuario (`tb_usuario`)

- `id` — Identificador único do usuário
- `altura` — Altura do usuário em metros
- `nome` — Nome completo do usuário
- `peso` — Peso corporal do usuário em kg
- `senha` — Senha criptografada do usuário
- `usuario` — Email do usuário

### Treino (`tb_treino`)

- `id` — Identificador único do treino
- `descricao` — Descrição detalhada do treino
- `repeticao` — Número de repetições por série
- `tempo_descanso` — Tempo de descanso entre séries
- `titulo` — Título resumido do treino
- `regiao_corporal_id` — Chave estrangeira para `tb_regiao_corporal`
- `usuario_id` — Chave estrangeira para tb_usuario

### Regiao Corporal (`tb_regiao_corporal`)

- `id` — Identificador único da região corporal
- `descricao` — Descrição da região corporal
- `nome` — Nome da região corporal (ex: Peitoral, Pernas)

📌 Relacionamentos
Cada Usuário (tb_usuario) pode ter múltiplos Treinos (tb_treino).

Cada Treino pertence a uma única Região Corporal (tb_regiao_corporal).

## Funcionalidades do CRUD

### Usuario

- Cadastrar: Cadastra um novo usuario
- Entrar: Permite o acesso do sistema ao usuario
- Listar: Visualiza todos os usuario (filtro por ID)
- Atualizar: Edita as informações de um usuario
- Checa o IMC: Permite o usuario atraves do ID checar o valor do IMC

### Treino

- Criar: Cadastra um novo treino
- Listar: Visualizar todos os treinos (filtra por ID e Titulo)
- Atualizar: Altera as informações de um treino
- Excluir: Deleta um treino especifico

### Região Corporal

- Criar: Cadastrar uma nova região corporal
- Listar: Visualizar todas as região corporais cadastradas (filtra por ID e por Descrição)
- Atualizar: Altera as informações de uma região corporal
- Excluir: Deletar uma região corporal especifica

## 💻 Tecnologias Utilizadas

| Tecnologia    | Descrição                                          |
| ------------- | -------------------------------------------------- |
| Java          | Linguagem principal do backend                     |
| Spring Boot   | Framework para desenvolvimento web                 |
| MySQL         | Banco de dados relacional                          |
| JPA/Hibernate | ORM para mapeamento objeto-relacional              |
| Maven         | Gerenciador de dependências                        |
| Insomnia      | Testes de endpoints RESTful                        |
| Trello        | Organização e gerenciamento das tarefas do projeto |

## 🧪 Testes

As funcionalidades da API foram testadas utilizando o Insomnia, simulando requisições HTTP para validação de cada endpoint.

## 👨‍💻 Integrantes do Grupo

| Responsavel      | Função        | GitHub                                                      |
| ---------------- | ------------- | ----------------------------------------------------------- |
| Weslley Ferreira | Developer     | [wdwf](https://github.com/wdwf/)                            |
| Ruan Barreto     | Tester        | [BarretoRuan](https://github.com/BarretoRuan)               |
| Rodrigo Henrique | Developer     | [RodrigoHenrikeH](https://github.com/RodrigoHenrikeH)       |
| Giulia Lopes     | Developer     | [Giulia-L-Ferreira](https://github.com/Giulia-L-Ferreira)   |
| Larissa Soares   | Product Owner | [LarissaSoaresSilva](https://github.com/LarissaSoaresSilva) |
| Elisa Bicudo     | Developer     | [eblopes23](https://github.com/eblopes23)                   |

📅 Projeto executado no dia: 25/04/2025

---

📌 Observações
Este projeto é voltado para fins educacionais e representa uma solução inicial que pode ser expandida com autenticação, agendamentos, relatórios e integração com frontend no futuro.
