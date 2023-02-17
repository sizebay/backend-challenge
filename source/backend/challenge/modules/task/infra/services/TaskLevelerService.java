package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.enums.TaskStatus;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.services.ITaskLevelerService;

import javax.inject.Singleton;

@Singleton
public class TaskLevelerService implements ITaskLevelerService {

    @Override
    public Task execute(Task task) {
        final var greaterThanHundred = task.getProgress() >= 100;
        task.setStatus(greaterThanHundred
                ? TaskStatus.COMPLETE : TaskStatus.PROGRESS);
        return task;
    }

}
