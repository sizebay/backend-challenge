package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RetrieveTaskByIdService implements IRetrieveTaskByIdService {

    private final ITaskRepository taskRepository;

    @Inject
    public RetrieveTaskByIdService(final ITaskRepository taskRepository) {this.taskRepository = taskRepository;}

    @Override
    public Task execute(Long taskId) {
        return taskRepository.index(taskId);
    }
}
