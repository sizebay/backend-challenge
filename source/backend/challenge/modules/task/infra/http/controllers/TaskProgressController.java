package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.IRetrieveTaskByIdService;
import backend.challenge.modules.task.services.IUpdateTaskProgressService;
import javax.inject.Inject;
import javax.inject.Singleton;
import kikaha.urouting.api.*;

@Singleton
@Path("tasks/progress")
public class TaskProgressController
{

	private final IUpdateTaskProgressService updateTaskProgressService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private static final String NOT_FOUND_MESSAGE = "Task not found for the given ID";


	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService,
		final IRetrieveTaskByIdService retrieveTaskByIdService)
	{
		this.updateTaskProgressService = updateTaskProgressService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") Long taskId, TaskProgressView taskProgressView)
	{

		try
		{

			if (taskProgressView.getProgress() < 1 || taskProgressView.getProgress() > 100)
			{
				return DefaultResponse.badRequest().entity("Please inform a value between 1 and 100");
			}
			TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

			taskProgressDTO.setId(taskId);
			taskProgressDTO.setProgress(taskProgressView.getProgress());

			Task taskToUpdate = retrieveTaskByIdService.execute(taskId);

			if (taskToUpdate == null)
			{
				return DefaultResponse.notFound().entity(NOT_FOUND_MESSAGE);
			}

			taskToUpdate = updateTaskProgressService.execute(taskProgressDTO);

			return DefaultResponse.ok().entity(taskToUpdate);

		}
		catch (Exception e)
		{
			return DefaultResponse.serverError().entity(e.getMessage());
		}
	}

}
