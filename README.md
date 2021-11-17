# Sizebay Backend Challenge

O teste está disponível tanto para candidatos quanto para quem quer apenas experimentar. As instruções se manterão as mesmas:

## Sobre

> A Sizebay é uma empresa que conecta conhecimentos de modelagem. No mercado desde 2014, nascemos considerando que poderíamos apenas de fato oferecer um produto inovador se considerássemos unir e oferecer o melhor conhecimento de produto e a mais avançada tecnologia da informação. Com um Provador Virtual que respeita a sua individualidade, Nossas recomendações são transparentes: exibimos as medidas do corpo e permitimos que sejam ajustadas. Consideramos como irá vestir em cada tipo de medida e em todos os tamanhos disponíveis.

# O desafio

Pediremos que você construa uma aplicação Rest API em Java, com o framework Kikaha, que permita ao usuário gerenciar tarefas, por meio de endpoints.

O usuário poderá criar, listar, atualizar e deletar tarefas. A tarefa terá a seguintes informações: título, descrição, progresso e status.

O template clonado está com vários `TODO` para implementar com sua criativade, seguindo todos os detalhes abaixo.

# Requisitos
- A aplicação deve ser em Java 11
- As rotas devem consumir e responder em JSON
- Completar todos os `TODO`
- A aplicação deve seguir o template fornecido.
- O projeto não pode ter erros.
- Implementar teste unitários nos serviços que não tenham teste unitários
- Todos os testes unitários devem passar e não podem ser ignorados.

# Rotas da aplicação
Com o template já clonado, você deve completar onde não possui código com o código para atingir os objetivos de cada rota.

`POST /tasks`: A rota deve receber title e description, sendo o `title` o título da tarefa e `description` uma descrição da tarefa. Ao cadastrar uma nova tarefa, ele deve receber como payload o seguindo corpo:

```json
{
  "title": "Completar desafio backend Sizebay",
  "description": ""
}
``` 

e armazenar o seguinte modelo:

```java
public class Task {

	private Long id;
	private String title;
	private String description;
	private int progress;
	private TaskStatus status;
	private Date createdAt;

}
``` 

Certifique-se que o `id` seja um UUID, e de sempre iniciar o `progress` como 0, o `status` como `PROGRESS`, e também de iniciar o `createdAt` sempre com a data atual.

**Possíveis status**: PROGRESS e COMPLETE

`GET /tasks`: Rota que lista todas as tarefas;

`GET /tasks/single/:taskId`: A rota deve retornar somente a tarefa a qual o id corresponder;

`PUT /tasks/single/:taskId`: A rota deve alterar apenas o title e description da tarefa que possua o id igual ao id correspondente nos parâmetros da rota.

```json
{
  "title": "Completar desafio backend Sizebay",
  "description": ""
}
``` 

`PUT /tasks/progress/single/:taskId`: A rota deve alterar apenas o progresso da tarefa que possua o id igual ao id correspondente nos parâmetros da rota. O `progress` pode ter o valor máximo de 100, e quando ele atingi o máximo, o `status` deve ser alterado para `COMPLETE`

```json
{
  "progress": 67,
}
``` 

`DELETE /tasks/single/:taskId`: A rota deve deletar a tarefa com o id correspondente nos parâmetros da rota;

# Específicação dos testes
Em cada teste, tem uma breve descrição no que sua aplicação deve cumprir para que o teste passe.

Para esse desafio temos os seguintes testes:

- `shouldBeAbleToCreateANewTask`: Para que esse teste passe, sua aplicação deve permitir que uma tarefa seja criado, e retorne um json com a tarefa criada.

- `shouldBeAbleToListTheTaskById`: Para que esse teste passe, sua aplicação deve permitir que seja retornado uma tarefa com o mesmo id informado.

- `shouldBeAbleToListTheTasks`: Para que esse teste passe, sua aplicação deve permitir que seja retornado um array com todas as tarefas que foram criadas até o momento.

- `shouldBeAbleToUpdateTask`: Para que esse teste passe, sua aplicação deve permitir que sejam alterados apenas os campos `title` e `observation`.

- `shouldNotBeAbleToUpdateATaskThatDoesNotExist`: Para que esse teste passe, você deve validar na sua rota de update se o id da tarefa enviada pela url existe ou não. Caso não exista, retornar um erro com status 400.

- `shouldNotBeAbleToUpdateTaskStatusManually`: Para que esse teste passe, você não deve permitir que sua rota de update altere diretamente o `status` dessa tarefa, mantendo o mesmo status que a terafa já possuia antes da atualização. Isso porque o único lugar que deve atualizar essa informação é a rota responsável por alterar o progresso da tarefa.

- `shouldBeAbleToUpdateTaskProgress`: Para que esse teste passe, sua aplicação deve permitir que sejam alterados apenas o campo `progress`.

- `shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred`: Para que esse teste passe, sua aplicação deve permitir que sejam alterado apenas o campo `status`, quando o progresso for igual a 100.

- `shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred`: Para que esse teste passe, você não deve permitir que sua rota de update de progresso de tarefa altere o progresso para menor que 0.

- `shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred`: Para que esse teste passe, você não deve permitir que sua rota de update de progresso de tarefa altere o progresso para maior que 100.

- `shouldBeAbleToDeleteTaskById`: Para que esse teste passe, sua aplicação deve permitir que tarefas sejam deletadas por id.

# Instruções

Para subir a aplicação, você deve rodar o teste unitário chamado `Runner` que se encontra dentro do package de tests. 
A aplicação iniciara na porta 8080. Ex: http://localhost:8080/tasks

# Tecnologias

Java (https://docs.oracle.com/en/java/)

Kikaha (https://skullabs.github.io/kikaha/docs/About-kikaha.html)

# Entrega

- Faça um fork desse template
- Ao finalizar, faça um Pull Request e nos avise via e-mail
- Aguarde o retorno

# Dúvidas?

Entre em contato com lucas@sizebay.com ou support@sizebay.com
