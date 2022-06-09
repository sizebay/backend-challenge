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

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") Long taskId, TaskProgressView taskProgressView) {

		try {

			TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
			taskProgressDTO.setId(taskId);
			taskProgressDTO.setProgress(taskProgressView.getProgress());
			Task task = updateTaskProgressService.execute(taskProgressDTO);

			if (taskProgressView.getProgress() > 100){
				return DefaultResponse.badRequest().entity("O progresso não pode ser maior que 100");
			} else if (task == null) {
				return DefaultResponse.badRequest().entity("Task não encontrada");
			}

			return DefaultResponse.ok().entity(task);

		} catch (Exception ex) {
			return DefaultResponse.badRequest().entity(ex.getMessage());
		}
	}

}
