package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.services.IUpdateTaskProgressService;
import backend.challenge.modules.task.infra.facades.ITaskProgressFacade;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final ITaskProgressFacade service;

	@Inject
	public TaskProgressController(final ITaskProgressFacade service) {
		this.service = service;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") String taskId, TaskProgressView taskProgressView) throws NotFoundTaskException, InvalidUpdateTaskException {
		service.update(UUID.fromString(taskId), taskProgressView);
		return DefaultResponse.ok();
	}

}
