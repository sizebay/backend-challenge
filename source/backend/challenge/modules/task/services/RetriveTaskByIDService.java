package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RetriveTaskByIDService implements IRetrieveTaskByIdService {
    private final ITaskRepository taskRepository;

    @Inject
    public RetriveTaskByIDService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long taskID) {
        Task requestedTask = this.taskRepository.index(taskID);

        return requestedTask;
    }
}
