package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.models.Task;

public interface ICreateTaskService {

	Task execute(TaskDTO taskDTO);

}
