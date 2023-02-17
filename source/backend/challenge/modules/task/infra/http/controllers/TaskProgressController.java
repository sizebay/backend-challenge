package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.services.IUpdateTaskProgressService;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") String taskId, TaskProgressView taskProgressView) throws NotFoundTaskException, InvalidUpdateTaskException {
		updateTaskProgressService.execute(TaskProgressDTO.builder()
				.id(UUID.fromString(taskId))
				.progress(taskProgressView.getProgress())
				.build());

		return DefaultResponse.ok();
	}

}
