package backend.challenge.modules.task.core.services;

import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;

public interface IUpdateTaskProgressService {

	Task execute(TaskProgressDTO taskProgressDTO) throws NotFoundTaskException, InvalidUpdateTaskException;

}
