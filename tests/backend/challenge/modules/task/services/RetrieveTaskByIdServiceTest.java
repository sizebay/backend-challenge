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
public class RetrieveTaskByIdServiceTest {

    private ITaskRepository taskRepository;
    private IRetrieveTaskByIdService retrieveTaskByIdService;
    private ICreateTaskService createTaskService;
    private UtilTest utilTest;

    @Before
    public void init() {
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        utilTest = new UtilTest();
    }

    public RetrieveTaskByIdServiceTest() {
        taskRepository = new TaskRepository();
    }

    @Test
    public void shouldBeAbleToListTheTaskById() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task taskCreated = createTaskService.execute(taskDTO);
        Task taskById = retrieveTaskByIdService.execute(taskCreated.getId());

        assertEquals(taskById.getId(), taskCreated.getId());
    }
}
