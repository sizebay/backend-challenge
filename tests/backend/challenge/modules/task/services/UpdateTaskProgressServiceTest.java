package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.util.UtilTest;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(KikahaRunner.class)
public class UpdateTaskProgressServiceTest {

    TaskRepository taskRepository;
    ICreateTaskService createTaskService;
    IUpdateTaskProgressService updateTaskProgressService;
    UtilTest utilTest;

    @Before
    public void init() {
        updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        utilTest = new UtilTest();
    }

    public UpdateTaskProgressServiceTest() {
        taskRepository = new TaskRepository();
    }

    @Test
    public void shouldBeAbleToUpdateTaskProgress() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);
        int oldTaskProgress = task.getProgress();
        TaskProgressDTO taskProgressDTO = utilTest.generateTaskProgressDTOWithRandomProgress(task.getId());

        if (taskProgressDTO.getProgress() > 100) {
            taskProgressDTO.setProgress(20);
        }

        Task taskUpdatedProgress = updateTaskProgressService.execute(taskProgressDTO);

        assertEquals(task.getId(), taskUpdatedProgress.getId());
        assertEquals(task.getTitle(), taskUpdatedProgress.getTitle());
        assertNotEquals(oldTaskProgress, taskUpdatedProgress.getProgress());
        assertEquals(task.getDescription(), taskUpdatedProgress.getDescription());
        assertEquals(task.getStatus(), taskUpdatedProgress.getStatus());
        assertEquals(task.getCreatedAt(), taskUpdatedProgress.getCreatedAt());
    }

    @Test
    public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {

        TaskDTO taskDTO = utilTest.createTaskDto();

        for (int i = 0; i < 5; i++) {
            createTaskService.execute(taskDTO);
        }

        List<Task> tasksToSetProgress = taskRepository.show();

        for (Task task : tasksToSetProgress) {
            TaskProgressDTO taskProgressDTO = utilTest.generateTaskProgressDTOWithRandomProgress(task.getId());
            updateTaskProgressService.execute(taskProgressDTO);
        }

        //criando uma task com progresso 100 para teste mais assertivo
        Task taskToValidateStatusComplete = createTaskService.execute(taskDTO);
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
        taskProgressDTO.setId(taskToValidateStatusComplete.getId());
        taskProgressDTO.setProgress(100);
        updateTaskProgressService.execute(taskProgressDTO);

        List<Task> tasksToValidateStatus = taskRepository.show();

        for (Task task : tasksToValidateStatus) {
            if (task.getProgress() == 100) {
                assertSame(task.getStatus(), TaskStatus.COMPLETE);
            } else {
                assertSame(task.getStatus(), TaskStatus.PROGRESS);
            }
        }
    }
}
