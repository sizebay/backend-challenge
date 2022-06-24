package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

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

            Task task = retrieveTaskByIdService.execute(taskId);

            if (task == null) {
                return DefaultResponse.notFound().entity("Nenhuma task foi encontrada com esta ID.");
            }

            List<String> mensagens = new ArrayList<>();

            if (taskProgressView.getProgress() == 100) {
                task.setStatus(TaskStatus.COMPLETE);
                mensagens.add("Progresso em 100%. Status atualizado para COMPLETE");
            }

            TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

            taskProgressDTO.setProgress(taskProgressView.getProgress());
            taskProgressDTO.setId(taskId);

            updateTaskProgressService.execute(taskProgressDTO);

            mensagens.add("Task atualizada com sucesso!");

            return DefaultResponse.ok().entity(mensagens);

        } catch (Exception e) {
            return DefaultResponse.badRequest().entity(e.getMessage());
        }

    }

}
