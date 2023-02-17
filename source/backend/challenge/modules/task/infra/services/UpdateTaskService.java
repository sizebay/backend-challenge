package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IRetrieveTaskByIdService;
import backend.challenge.modules.task.core.services.ITaskLevelerService;
import backend.challenge.modules.task.core.services.IUpdateTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskService implements IUpdateTaskService {

    private final ITaskRepository taskRepository;
    private final IRetrieveTaskByIdService retrieveTaskByIdService;
    private final ITaskLevelerService taskLeveler;

    @Inject
    public UpdateTaskService(final ITaskRepository taskRepository,
                             final IRetrieveTaskByIdService retrieveTaskByIdService,
                             final ITaskLevelerService taskLeveler) {
        this.retrieveTaskByIdService = retrieveTaskByIdService;
        this.taskRepository = taskRepository;
        this.taskLeveler = taskLeveler;
    }

    @Override
    public Task execute(Task task) throws NotFoundTaskException, InvalidUpdateTaskException {
        final var taskInMemory = retrieveTaskByIdService.execute(task.getId());
        validEqualsStatus(task, taskInMemory);

        taskLeveler.execute(task);
        return taskRepository.update(task);
    }

    private void validEqualsStatus(Task task, Task taskInMemory) throws InvalidUpdateTaskException {
        if (task.getStatus() != taskInMemory.getStatus())
            throw new InvalidUpdateTaskException();
    }
}
