package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;

public class Utils
{
	public static TaskDTO newTaskDTO(){
		TaskDTO taskDTO = TaskDTO.create();

		taskDTO.setTitle("Title Test");
		taskDTO.setDescription("Description Test");

		return taskDTO;
	}

	public static TaskDTO newTaskDTOToUpdate(){
		TaskDTO taskDTO = TaskDTO.create();

		taskDTO.setTitle("Test Title");
		taskDTO.setDescription("Test Description");

		return taskDTO;
	}

	public static TaskProgressDTO newTaskProgressDTO(Long taskId) {
		TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

		taskProgressDTO.setId(taskId);
		taskProgressDTO.setProgress(63);

		return taskProgressDTO;
	}
}
