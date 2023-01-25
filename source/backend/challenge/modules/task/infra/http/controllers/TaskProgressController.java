package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
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

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") Long taskId, TaskProgressView taskProgressView) {
        if (taskProgressView.getProgress() < 0) {
            return DefaultResponse.badRequest().entity("Progress cannot be smaller than 0");
        }

        if (taskProgressView.getProgress() > 100) {
            return DefaultResponse.badRequest().entity("Progress cannot be greater than 100");
        }

		TaskProgressDTO taskProgressDTO = new TaskProgressDTO(taskId, taskProgressView.getProgress());
		Task task = updateTaskProgressService.execute(taskProgressDTO);

        if (task == null) {
            return DefaultResponse.notFound().entity("Task not found");
        }

        if (taskProgressView.getProgress() == 100) {
            task.setStatus(TaskStatus.COMPLETE);
        }

		return DefaultResponse.ok().entity(task);
	}

}
