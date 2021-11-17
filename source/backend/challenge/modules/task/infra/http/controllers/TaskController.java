package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = null;
		this.updateTaskService = null;
	}

	@GET
	public Response show() {
		// TODO: Rota que lista todas as tarefas

		return DefaultResponse.ok().entity("Hello world");
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		// TODO: A rota deve retornar somente a tarefa a qual o id corresponder

		return DefaultResponse.ok().entity("Hello world");
	}

	@POST
	public Response create(TaskView task) {
		// TODO: A rota deve receber title e description, sendo o `title` o título da tarefa e `description` uma descrição da tarefa.

		return DefaultResponse.ok().entity("Hello world");
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		/*
			TODO:  A rota deve alterar apenas o title e description da tarefa
			 			 que possua o id igual ao id correspondente nos parâmetros da rota.
		 */

		return DefaultResponse.ok().entity("Hello world");
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		// TODO: A rota deve deletar a tarefa com o id correspondente nos parâmetros da rota

		return DefaultResponse.ok().entity("Hello world");
	}

}
