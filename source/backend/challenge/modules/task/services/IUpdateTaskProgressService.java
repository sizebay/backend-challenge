package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.models.Task;

public interface IUpdateTaskProgressService {

	Task execute(TaskProgressDTO taskProgressDTO);

}
