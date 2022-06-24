package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(KikahaRunner.class)
public class UpdateTaskServiceTest {

    private IRetrieveAllTasksService retrieveAllTasksService;
    private IRetrieveTaskByIdService retrieveTaskByIdService;
    private ICreateTaskService createTaskService;
    private IUpdateTaskService updateTaskService;
    private IDeleteTaskService deleteTaskService;
    private TaskController taskContoller;
    private UtilTestsTasks utilTestsTasks;

    @Before
    public void init() {
        final TaskRepository taskRepository = new TaskRepository();
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
        createTaskService = new CreateTaskService(taskRepository);
        updateTaskService = new UpdateTasksService(taskRepository);
        utilTestsTasks = new UtilTestsTasks();
        taskContoller = new TaskController(createTaskService, deleteTaskService, retrieveAllTasksService, retrieveTaskByIdService, updateTaskService);
    }

    @Test
    public void shouldBeAbleToUpdateTask() {
		/**
		 *  OBS: EM RELAÇÃO AO DADO "OBSERVATION" DO TO DO, JULGUEI SER DESCRIÇÃO!.
		 */
		TaskDTO taskDTO = utilTestsTasks.createTask();

		Task task = createTaskService.execute(taskDTO);
		Task taskPreserved = utilTestsTasks.preserveTaskValues(task);

		task = utilTestsTasks.changeDescriptionAndTitle(task);
		taskContoller.update(task.getId(), task);

		Task taskTest = retrieveTaskByIdService.execute(task.getId());

        assertNotEquals(taskTest.getTitle(), taskPreserved.getTitle());
        assertNotEquals(taskTest.getDescription(), taskPreserved.getDescription());
        assertEquals(taskTest.getProgress(), taskPreserved.getProgress());
        assertEquals(taskTest.getStatus(), taskPreserved.getStatus());
		assertEquals(taskTest.getCreatedAt().toString(), taskPreserved.getCreatedAt().toString());

    }

    @Test
    public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
        TaskDTO taskDTO = utilTestsTasks.createTask();

        Task task = createTaskService.execute(taskDTO);

        task = utilTestsTasks.changeDescriptionAndTitle(task);
        Response response = taskContoller.update(utilTestsTasks.getRandomId(), task);

        assertEquals(response.statusCode(), 400);
    }

    @Test
    public void shouldNotBeAbleToUpdateTaskStatusManually() {
        TaskDTO taskDTO = utilTestsTasks.createTask();

        Task task = createTaskService.execute(taskDTO);

        task = utilTestsTasks.changeDescriptionAndTitle(task);
        task.setStatus(TaskStatus.COMPLETE);
        taskContoller.update(utilTestsTasks.getRandomId(), task);

        Task taskTest = retrieveTaskByIdService.execute(task.getId());

        assertEquals(task.getStatus(), taskTest.getStatus());
    }


}