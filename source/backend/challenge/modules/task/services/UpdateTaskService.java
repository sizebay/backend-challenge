package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskService implements  IUpdateTaskService {

    private final ITaskRepository taskRepository;
    @Inject
    public UpdateTaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Task task, Task newTask) {
        return taskRepository.update(task);
    }
}
