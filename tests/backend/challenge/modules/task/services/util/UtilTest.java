package backend.challenge.modules.task.services.util;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;

import java.util.Random;

public class UtilTest {


    public TaskDTO createTaskDto(){
        TaskDTO taskDTO = TaskDTO.create();
		taskDTO.setDescription("");
		taskDTO.setTitle("Completar desafio backend Sizebay2");

        return taskDTO;
    }


    public TaskProgressDTO generateTaskProgressDTOWithRandomProgress(Long taskId) {
        Random randomNumber = new Random();

        int valor = randomNumber.nextInt(120) + 1;
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
        taskProgressDTO.setId(taskId);
        taskProgressDTO.setProgress(valor);

        return taskProgressDTO;
    }
}
