package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
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
public class RetrieveAllTasksServiceTest {

    private TaskRepository taskRepository;
    private IRetrieveAllTasksService retrieveAllTasksService;
    private ICreateTaskService createTaskService;
    private UtilTest utilTest;

    @Before
    public void init() {
        retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        utilTest = new UtilTest();
    }

    public RetrieveAllTasksServiceTest() {
        this.taskRepository = new TaskRepository();
    }

    @Test
    public void shouldBeAbleToListTheTasks() {

        int quantityOfTasksToCreate = 5;
        TaskDTO taskDTO = utilTest.createTaskDto();

        for (int i = 0; i < 5; i++) {
            createTaskService.execute(taskDTO);
        }

        List<Task> tasks = retrieveAllTasksService.execute();
        assertEquals(tasks.size(), quantityOfTasksToCreate);
    }
}