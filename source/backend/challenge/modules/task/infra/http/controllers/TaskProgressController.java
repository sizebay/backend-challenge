package backend.challenge.modules.task.infra.http.controllers;

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
		/*
			TODO: A rota deve alterar apenas o progresso da tarefa que possua o id igual ao id correspondente
			 			nos parâmetros da rota.
			 			O `progress` pode ter o valor máximo de 100, e quando ele atingi o máximo,
			 			o `status` deve ser alterado para `COMPLETE`
		 */

		return DefaultResponse.ok().entity("Hello world");
	}

}
