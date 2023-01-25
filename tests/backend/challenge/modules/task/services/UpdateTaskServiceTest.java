package backend.challenge.modules.task.services;

import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private ICreateTaskService createTaskService;
	private IDeleteTaskService deleteTaskService;
	private IRetrieveAllTasksService retrieveAllTasksService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private IUpdateTaskService updateTaskService;
	private TaskController taskController;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository);
		taskController = new TaskController(createTaskService, deleteTaskService, retrieveAllTasksService, retrieveTaskByIdService, updateTaskService);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		TaskDTO taskDTO = new TaskDTO("A", "A");
		Task taskA = createTaskService.execute(taskDTO);
		Task taskB = new Task();
		taskB.setId(taskA.getId());
		taskB.setTitle("B");
		taskB.setDescription("B");
		updateTaskService.execute(taskB);
        Task taskRetrieved = retrieveTaskByIdService.execute(taskA.getId());
        deleteTaskService.execute(taskA.getId());
        assertEquals(taskRetrieved.getTitle(), taskB.getTitle());
        assertEquals(taskRetrieved.getDescription(), taskB.getDescription());
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		Task task = new Task();
		task.setId(0);
		task.setTitle("");
		task.setDescription("");
        Response response = taskController.update(task.getId(), task);
        assertEquals(response.statusCode(), 400);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		TaskDTO taskDTO = new TaskDTO("A", "A");
		Task taskA = createTaskService.execute(taskDTO);
		Task taskB = new Task();
		taskB.setId(0);
		taskB.setTitle("B");
		taskB.setDescription("B");
		updateTaskService.execute(taskB);
        Task taskRetrieved = retrieveTaskByIdService.execute(taskA.getId());
        deleteTaskService.execute(taskRetrieved.getId());
        assertEquals(taskRetrieved.getStatus(), TaskStatus.PROGRESS);
	}

}
