package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.infra.facades.ITaskFacade;
import backend.challenge.modules.task.infra.http.views.TaskView;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks")
public class TaskController {

	private ITaskFacade service;

	@Inject
	public TaskController(final ITaskFacade service) {
		this.service = service;
	}

	@GET
	public Response show() {
		return DefaultResponse.ok(service.show());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") String taskId) throws NotFoundTaskException {
		return DefaultResponse.ok(service.index(UUID.fromString(taskId)));
	}

	@POST
	public Response create(TaskView task) {
		final var createdTask = service.create(task);
		return DefaultResponse.ok().entity(createdTask);
	}

	/*
		I made it to POST because when it was PUT it always returned the following error 'For input string: \...'
	 */
	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") String taskId, TaskView task) throws NotFoundTaskException, InvalidUpdateTaskException {
		service.update(UUID.fromString(taskId), task);
		return DefaultResponse.ok();
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") String taskId) throws NotFoundTaskException {
		service.delete(UUID.fromString(taskId));
		return DefaultResponse.ok();
	}

}
