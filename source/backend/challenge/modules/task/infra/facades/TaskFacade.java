package backend.challenge.modules.task.infra.facades;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.services.*;
import backend.challenge.modules.task.infra.http.views.TaskView;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskFacade implements ITaskFacade {

    private final ICreateTaskService createTaskService;
    private final IDeleteTaskService deleteTaskService;
    private final IRetrieveAllTasksService retrieveAllTasksService;
    private final IRetrieveTaskByIdService retrieveTaskByIdService;
    private final IUpdateTaskService updateTaskService;

    @Inject
    public TaskFacade(
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

    @Override
    public List<Task> show() {
        return this.retrieveAllTasksService.execute();
    }

    @Override
    public Task index(UUID taskId) throws NotFoundTaskException {
        return this.retrieveTaskByIdService.execute(taskId);
    }

    @Override
    public Task create(TaskView task) {
        return createTaskService.execute(TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .build());
    }

    @Override
    public void update(UUID taskId, TaskView task) throws NotFoundTaskException, InvalidUpdateTaskException {
        final var taskInMemory = retrieveTaskByIdService.execute(taskId).clone();
        taskInMemory.setTitle(task.getTitle());
        taskInMemory.setDescription(task.getDescription());
        updateTaskService.execute(taskInMemory);
    }

    @Override
    public void delete(UUID taskId) throws NotFoundTaskException {
        deleteTaskService.execute(taskId);
    }
}
