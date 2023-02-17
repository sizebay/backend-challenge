package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IUpdateTaskProgressService;
import backend.challenge.modules.task.core.services.IUpdateTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;
    private final IUpdateTaskService updateTaskService;

    @Inject
    public UpdateTaskProgressService(final ITaskRepository taskRepository,
                                     final IUpdateTaskService updateTaskService) {
        this.taskRepository = taskRepository;
        this.updateTaskService = updateTaskService;
    }

    @Override
    public Task execute(TaskProgressDTO taskProgressDTO) throws NotFoundTaskException, InvalidUpdateTaskException {
        final var taskInMemory = taskRepository.index(taskProgressDTO.getId()).clone();
        taskInMemory.setProgress(taskProgressDTO.getProgress());
        return updateTaskService.execute(taskInMemory);
    }

}
