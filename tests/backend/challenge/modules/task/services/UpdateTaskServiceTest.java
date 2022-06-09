package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.util.UtilTest;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(KikahaRunner.class)
public class UpdateTaskServiceTest {

    private ICreateTaskService createTaskService;
    private IDeleteTaskService deleteTaskService;
    private IRetrieveAllTasksService retrieveAllTasksService;
    private IRetrieveTaskByIdService retrieveTaskByIdService;
    private IUpdateTaskService updateTaskService;
    private TaskRepository taskRepository;
    private TaskController taskController;
    private UtilTest utilTest;

    @Before
    public void init() {
        updateTaskService = new UpdateTaskService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
        deleteTaskService = new DeleteTaskService(taskRepository);
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
        taskController = new TaskController(createTaskService, deleteTaskService, retrieveAllTasksService, retrieveTaskByIdService, updateTaskService);
        utilTest = new UtilTest();
    }

    public UpdateTaskServiceTest() {
        taskRepository = new TaskRepository();
    }

    @Test
    public void shouldBeAbleToUpdateTask() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);

        Task taskToTryUpdate = createTaskService.execute(taskDTO);
        updatabilityInTask(taskToTryUpdate);
        taskController.update(task.getId(), taskToTryUpdate);

        assertEquals(task.getTitle(), taskToTryUpdate.getTitle());
        assertEquals(task.getDescription(), taskToTryUpdate.getDescription());
        assertNotEquals(task.getProgress(), taskToTryUpdate.getProgress());
        assertNotEquals(task.getStatus(), taskToTryUpdate.getStatus());
        assertEquals(task.getCreatedAt().toString(), taskToTryUpdate.getCreatedAt().toString());
    }

    @Test
    public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);
        Task taskToTestWhenExistId = retrieveTaskByIdService.execute(task.getId());

        Response resultWhenExists = taskController.index(taskToTestWhenExistId.getId());
        Response resultNotExists = taskController.index(5L);

        assertEquals(resultNotExists.statusCode(), 404);
        assertEquals(resultWhenExists.statusCode(), 200);
    }

    @Test
    public void shouldNotBeAbleToUpdateTaskStatusManually() {

        TaskDTO taskDTO = utilTest.createTaskDto();
        Task task = createTaskService.execute(taskDTO);

        Task taskToTryUpdate = createTaskService.execute(taskDTO);
        taskToTryUpdate.setProgress(20);

        taskController.update(task.getId(), taskToTryUpdate);
        Task taskAfterUpdate = retrieveTaskByIdService.execute(task.getId());

        assertEquals(task.getProgress(), taskAfterUpdate.getProgress());
    }

    private void updatabilityInTask(Task taskToTryUpdate) {
        taskToTryUpdate.setProgress(85);
        taskToTryUpdate.setTitle("Testando alteração");
        taskToTryUpdate.setDescription("Testando alteração");
        taskToTryUpdate.setStatus(TaskStatus.COMPLETE);
        taskToTryUpdate.setCreatedAt(new Date());
    }


}