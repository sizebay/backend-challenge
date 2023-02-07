package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kikaha.urouting.api.*;

@Singleton
@Path("tasks")
public class TaskController
{

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;
	private static final String NOT_FOUND_MESSAGE = "Task not found for the given ID";

	@Inject
	public TaskController(final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService)
	{
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show()
	{

		try
		{
			List<Task> tasks = retrieveAllTasksService.execute();

			return DefaultResponse.ok().entity(tasks);

		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId)
	{

		try
		{
			Task task = retrieveTaskByIdService.execute(taskId);

			if (task == null)
			{
				return DefaultResponse.notFound().entity(NOT_FOUND_MESSAGE);
			}
			return DefaultResponse.ok().entity(task);

		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}

	@POST
	public Response create(TaskView task)
	{

		try
		{
			TaskDTO taskDTO = TaskDTO.create();

			taskDTO.setTitle(task.getTitle());
			taskDTO.setDescription(task.getDescription());

			Task newTask = createTaskService.execute(taskDTO);

			return DefaultResponse.ok().entity(newTask);
		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task)
	{
		try
		{
			Task taskToUpdate = retrieveTaskByIdService.execute(taskId);

			if (taskToUpdate == null)
			{
				return DefaultResponse.notFound().entity(NOT_FOUND_MESSAGE);
			}

			return DefaultResponse.ok().entity(updateTaskService.execute(taskToUpdate, task));

		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId)
	{
		try
		{
			Task taskToDelete = retrieveTaskByIdService.execute(taskId);

			if (taskToDelete == null)
			{
				return DefaultResponse.notFound().entity(NOT_FOUND_MESSAGE);
			}

			deleteTaskService.execute(taskId);

			return DefaultResponse.noContent();

		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}
}
