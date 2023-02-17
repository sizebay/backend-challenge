package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IRetrieveTaskByIdService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class RetrieveTaskByIdService implements IRetrieveTaskByIdService {

    private final ITaskRepository taskRepository;

    @Inject
    public RetrieveTaskByIdService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(UUID taskId) throws NotFoundTaskException {
        return taskRepository.index(taskId);
    }

}
