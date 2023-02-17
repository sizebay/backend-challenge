package backend.challenge.modules.task.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.enums.TaskStatus;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.IRetrieveTaskByIdService;
import backend.challenge.modules.task.core.services.IUpdateTaskService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.CreateTaskService;
import backend.challenge.modules.task.infra.services.RetrieveTaskByIdService;
import backend.challenge.modules.task.infra.services.TaskLevelerService;
import backend.challenge.modules.task.infra.services.UpdateTaskService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private ITaskRepository taskRepository;
	private ICreateTaskService createTaskService;
	private IUpdateTaskService updateTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
	public void init() {
		final var taskLeveler = new TaskLevelerService();

		taskRepository = new LocalTaskRepository();
		createTaskService = new CreateTaskService(taskRepository, taskLeveler);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository, retrieveTaskByIdService, taskLeveler);
	}

	@Test
	public void shouldBeAbleToUpdateTask() throws NotFoundTaskException, InvalidUpdateTaskException {
		final var task = createTaskService.execute(TaskDTO.builder()
				.title("Some title")
				.build());

		final var newTitle = "New some title";
		task.setTitle(newTitle);
		updateTaskService.execute(task);

		final var retriviedTask = retrieveTaskByIdService.execute(task.getId());
		Assert.assertEquals(retriviedTask.getTitle(), newTitle);
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() throws InvalidUpdateTaskException {
		final var task = createTaskService.execute(TaskDTO.builder()
				.title("Some title")
				.build());

		final var newTask = task.clone();
		newTask.setId(UUID.fromString("01617e15-c5da-4229-9e63-9516e73f7bfe"));
		try {
			updateTaskService.execute(newTask);
		} catch (NotFoundTaskException e) {
			return;
		}

		Assert.fail();
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() throws NotFoundTaskException {
		final var task = createTaskService.execute(TaskDTO.builder()
				.title("Some title")
				.build());

		final var newTask = task.clone();
		newTask.setStatus(TaskStatus.COMPLETE);
		try {
			updateTaskService.execute(newTask);
		} catch (InvalidUpdateTaskException e) {
			return;
		}

		Assert.fail();
	}


}