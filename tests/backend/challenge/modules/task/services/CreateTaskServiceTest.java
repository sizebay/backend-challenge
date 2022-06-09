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
public class CreateTaskServiceTest {

    private ICreateTaskService createTaskService;
    private UtilTest utilTest;

    @Before
    public void init() {
        final ITaskRepository taskRepository = new TaskRepository();
        createTaskService = new CreateTaskService(taskRepository);
        utilTest = new UtilTest();
    }

    @Test
    public void shouldBeAbleToCreateANewTask() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);
        assertNotNull(task.getId());
    }
}