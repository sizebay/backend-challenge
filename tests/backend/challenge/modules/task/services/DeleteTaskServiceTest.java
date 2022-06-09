package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.util.UtilTest;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(KikahaRunner.class)
public class DeleteTaskServiceTest {

    private ITaskRepository taskRepository;
    private IDeleteTaskService deleteTaskService;
    private ICreateTaskService createTaskService;
    private UtilTest utilTest;

    @Before
    public void init() {
        deleteTaskService = new DeleteTaskService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        utilTest = new UtilTest();
    }

    public DeleteTaskServiceTest() {
        this.taskRepository = new TaskRepository();
    }

    @Test
    public void shouldBeAbleToDeleteTaskById() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);
        deleteTaskService.execute(task.getId());
        Task taskExists = taskRepository.index(task.getId());

        assertNull(taskExists);
    }
}