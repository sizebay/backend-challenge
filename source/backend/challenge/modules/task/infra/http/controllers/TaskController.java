package backend.challenge.modules.task.infra.http.controllers;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Path("tasks")
public class TaskController {

    private final ICreateTaskService createTaskService;
    private final IDeleteTaskService deleteTaskService;
    private final IRetrieveAllTasksService retrieveAllTasksService;
    private final IRetrieveTaskByIdService retrieveTaskByIdService;
    private final IUpdateTaskService updateTaskService;

    @Inject
    public TaskController(
            final ICreateTaskService createTaskService,
            final IDeleteTaskService deleteTaskService,
            final IRetrieveAllTasksService retrieveAllTasksService,
            final IRetrieveTaskByIdService retrieveTaskByIdService,
            final IUpdateTaskService updateTaskService
    ) {
        this.createTaskService = createTaskService;
        this.deleteTaskService = deleteTaskService;
        this.retrieveAllTasksService = retrieveAllTasksService;
        this.retrieveTaskByIdService = retrieveTaskByIdService;
        this.updateTaskService = updateTaskService;
    }

    @GET
    public Response show() {

        try {

            List<Task> tasks = retrieveAllTasksService.execute();

            if (tasks.size() == 0) {
                return DefaultResponse.badRequest().entity("Nenhuma task registrada");
            } else {
                return DefaultResponse.ok().entity(tasks);
            }

        } catch (Exception ex) {
            return DefaultResponse.badRequest().entity(ex.getMessage());
        }
    }

    @GET
    @Path("single/{taskId}")
    public Response index(@PathParam("taskId") Long taskId) {

        try {
            Task task = retrieveTaskByIdService.execute(taskId);

            if (task == null) {
                return DefaultResponse.notFound().entity("Task não encontrada");
            }
            return DefaultResponse.ok().entity(task);
        } catch (Exception ex) {
            return DefaultResponse.badRequest().entity(ex.getMessage());
        }
    }

    @POST
    public Response create(TaskView task) {

        try {
            TaskDTO taskDTO = TaskDTO.create();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            Task taskCreated = createTaskService.execute(taskDTO);

            return DefaultResponse.created().entity(taskCreated);
        } catch (Exception ex) {
            return DefaultResponse.badRequest().entity(ex.getMessage());
        }
    }

    @PUT
    @Path("single/{taskId}")
    public Response update(@PathParam("taskId") Long taskId, Task task) {

        try {
            Task taskToUpdate = retrieveTaskByIdService.execute(taskId);

            if (taskToUpdate == null) {
                return DefaultResponse.notFound().entity("Task não encontrada");
            }
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDescription(task.getDescription());
            return DefaultResponse.ok().entity(updateTaskService.execute(taskToUpdate));

        } catch (Exception ex) {
            return DefaultResponse.badRequest().entity(ex.getMessage());
        }
    }

    @DELETE
    @Path("single/{taskId}")
    public Response delete(@PathParam("taskId") Long taskId) {

        try {

            Task taskExists = retrieveTaskByIdService.execute(taskId);
            if (taskExists == null) {
                return DefaultResponse.notFound().entity("Task não encontrada");
            }

            deleteTaskService.execute(taskId);
            Task task = retrieveTaskByIdService.execute(taskId);

            if (task == null) {
                return DefaultResponse.ok().entity("Task removida com sucesso");
            } else {
                return DefaultResponse.badRequest().entity("Ocorreu um erro ao deletar");
            }

        } catch (Exception ex) {
            return DefaultResponse.badRequest().entity(ex.getMessage());
        }
    }
}
