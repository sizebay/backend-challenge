package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

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
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show() {

		try{
			List<Task> lsTasks = retrieveAllTasksService.execute();

			if (lsTasks != null) {
				return DefaultResponse.ok().entity(lsTasks);
			} else {
				return DefaultResponse.noContent().entity("Nenhuma task encontrada.");
			}

		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {

		try {
			Task task = retrieveTaskByIdService.execute(taskId);

			if (task != null) {
				return DefaultResponse.ok().entity(task);
			}

			return DefaultResponse.noContent().entity("Nenhuma tarefa encontrada.");
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}

	}

	@POST
	public Response create(TaskView task) {

		try {
			List<String> validation = new ArrayList<String>();
			if (task.getTitle() == null || task.getTitle().isEmpty()) {
				validation.add("Campo TÍTULO não pode estar vazio.");
			}
			if (task.getDescription() == null || task.getDescription().isEmpty()) {
				validation.add("Campo DESCRIÇÃO não pode estar vazio.");
			}

			if (!validation.isEmpty()) {
				return DefaultResponse.notFound().entity(validation);
			}

			TaskDTO newTaskDto = TaskDTO.create();
			newTaskDto.setTitle(task.getTitle());
			newTaskDto.setDescription(task.getDescription());

			Task newTask = createTaskService.execute(newTaskDto);

			return DefaultResponse.created().entity(newTask);

		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}

	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {

		try {
			List<String> validation = new ArrayList<String>();
			if (task.getTitle() == null || task.getTitle().isEmpty()) {
				validation.add("Campo TÍTULO não pode estar vazio.");
			}
			if (task.getDescription() == null || task.getDescription().isEmpty()) {
				validation.add("Campo DESCRIÇÃO não pode estar vazio.");
			}

			if (!validation.isEmpty()) {
				return DefaultResponse.notFound().entity(validation);
			}

			if (retrieveTaskByIdService.execute(taskId) == null) {
				return DefaultResponse.badRequest().entity("Nenhuma task foi encontrada com esta ID.");
			} else {
				task.setId(taskId);
			}

			return DefaultResponse.ok().entity(updateTaskService.execute(task));

		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {

		try {

			if (retrieveTaskByIdService.execute(taskId) == null) {
				return DefaultResponse.notFound().entity("Nenhuma task foi encontrada com esta ID.");
			} else {
				deleteTaskService.execute(taskId);
				return DefaultResponse.ok().entity("Task removida com sucesso!");
			}

		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

}
