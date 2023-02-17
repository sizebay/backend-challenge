package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.enums.TaskStatus;
import backend.challenge.modules.task.core.models.Task;

public interface ITaskLevelerService {

    Task execute(Task task);

}
