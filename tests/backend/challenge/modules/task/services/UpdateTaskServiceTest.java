package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;
	private ICreateTaskService createTaskService;
	private TaskController taskController;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository);
		IRetrieveTaskByIdService retrieveTaskByIdService = new RetriveTaskByIDService(taskRepository);
		IRetrieveAllTasksService retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		IDeleteTaskService deleteTaskService = new DeleteTaskService(taskRepository);
		taskController = new TaskController(createTaskService, deleteTaskService,
				retrieveAllTasksService,
				retrieveTaskByIdService, updateTaskService);

	}
	@Test
	public void shouldBeAbleToUpdateTask() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);

		TaskDTO newTaskDTO = Utils.newTaskDTOUpdate();
		Task newTask = createTaskService.execute(newTaskDTO);
		Task updateTask = updateTaskService.execute(standardTask, newTask);
		Assert.assertEquals(updateTask, standardTask);
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		Response httpStatus = taskController.index(1234L);
		Assert.assertEquals(httpStatus.statusCode(),404);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);

		TaskDTO newTaskDTO = Utils.newTaskDTOUpdate();
		Task newTask = createTaskService.execute(newTaskDTO);
		newTask.setStatus(TaskStatus.COMPLETE);
		Task updatedTask = updateTaskService.execute(standardTask, newTask);

		Assert.assertEquals(standardTask.getStatus(), updatedTask.getStatus());
	}


}