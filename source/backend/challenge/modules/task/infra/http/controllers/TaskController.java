package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import java.util.List;

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
		List<Task> tasks = retrieveAllTasksService.execute();

		if (tasks.size() == 0) {
			DefaultResponse.badRequest().entity("No task found");
		}

		return DefaultResponse.ok().entity(tasks);
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		Task task = retrieveTaskByIdService.execute(taskId);

		if (task == null) {
			DefaultResponse.notFound().entity("Task not found");
		}

		return DefaultResponse.ok().entity(task); 
	}

	@POST
	public Response create(TaskView taskView) {
		TaskDTO taskDTO = new TaskDTO(taskView.getTitle(), taskView.getDescription());
        Task task = createTaskService.execute(taskDTO);
        return DefaultResponse.created().entity(task);
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		Task retrievedTask = retrieveTaskByIdService.execute(taskId);

        if (retrievedTask == null) {
            return DefaultResponse.badRequest().entity("Task not found");
        }

        updateTaskService.execute(task);
        return DefaultResponse.ok().entity("Task updated");
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
        boolean taskRemoved = deleteTaskService.execute(taskId);

        if (!taskRemoved) {
            return DefaultResponse.badRequest().entity("Task not found");
        }

		return DefaultResponse.ok().entity("Task removed");
	}

}
