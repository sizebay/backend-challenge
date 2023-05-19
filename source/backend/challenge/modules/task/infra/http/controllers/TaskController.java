package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
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
		try {
			List<Task> allTasks = retrieveAllTasksService.execute();
			return DefaultResponse.ok().entity(allTasks);
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		try {
			Task task = retrieveTaskByIdService.execute(taskId);
			if (task == null) {
				return DefaultResponse.notFound();
			}
			return DefaultResponse.ok().entity(task);
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

	@POST
	public Response create(TaskView task) {
		try {
			TaskDTO taskDto = TaskDTO.create();
			taskDto.setTitle(task.getTitle());
			taskDto.setDescription(task.getDescription());
			Task newTask = createTaskService.execute(taskDto);
			return DefaultResponse.created().entity(newTask);
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		try {
			Task updateTask = retrieveTaskByIdService.execute(taskId);
			if (updateTask == null) {
				return DefaultResponse.notFound();
			}
			updateTask.setTitle(task.getTitle());
			updateTask.setDescription(task.getDescription());
			Task updatedTask = updateTaskService.execute(updateTask, task);
			return DefaultResponse.ok().entity(updatedTask);
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}

	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		try {
			Task deleteTask = retrieveTaskByIdService.execute(taskId);
			if (deleteTask == null) {
				return DefaultResponse.notFound();
			}
			deleteTaskService.execute(taskId);

			return DefaultResponse.noContent();
		}catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}

	}

}
