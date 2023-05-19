package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;

	@Inject
	public TaskProgressController(
			final IUpdateTaskProgressService updateTaskProgressService,
			final IRetrieveTaskByIdService retrieveTaskByIdService
	) {
		this.updateTaskProgressService = updateTaskProgressService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") Long taskId, TaskProgressView taskProgressView) {
		try {
			if (taskProgressView.getProgress() < 1 || taskProgressView.getProgress() > 100) {
				return DefaultResponse.badRequest().entity("Enter a value between 1 and 100");
			}

			TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
			taskProgressDTO.setId(taskId);
			taskProgressDTO.setProgress(taskProgressView.getProgress());
			Task taskToUpdate = retrieveTaskByIdService.execute(taskId);

			if (taskToUpdate == null) {
				return DefaultResponse.notFound();
			}

			taskToUpdate = updateTaskProgressService.execute(taskProgressDTO);
			return DefaultResponse.ok().entity(taskToUpdate);
		} catch (Exception e) {
			return DefaultResponse.badRequest().entity(e.getMessage());
		}
	}
}
