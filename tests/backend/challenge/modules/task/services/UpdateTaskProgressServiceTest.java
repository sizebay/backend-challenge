package backend.challenge.modules.task.services;


import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.enums.TaskStatus;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.IRetrieveTaskByIdService;
import backend.challenge.modules.task.core.services.IUpdateTaskProgressService;
import backend.challenge.modules.task.core.services.IUpdateTaskService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.*;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private ITaskRepository taskRepository;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private IUpdateTaskProgressService updateTaskProgressService;

	@Before
	public void init() {
		final var taskLeveler = new TaskLevelerService();

		taskRepository = new LocalTaskRepository();
		createTaskService = new CreateTaskService(taskRepository, taskLeveler);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);

		final var updateTaskService = new UpdateTaskService(taskRepository, retrieveTaskByIdService, taskLeveler);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository, updateTaskService);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() throws NotFoundTaskException, InvalidUpdateTaskException {
		final var task = createTaskService.execute(TaskDTO.builder()
				.title("Some title")
				.build());

		final int newProgress = 50;
		updateTaskProgressService.execute(TaskProgressDTO.builder()
				.id(task.getId())
				.progress(newProgress)
				.build());

		final var retriviedTask = retrieveTaskByIdService.execute(task.getId());
		Assert.assertEquals(retriviedTask.getProgress(), newProgress);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() throws NotFoundTaskException, InvalidUpdateTaskException {
		final var task = createTaskService.execute(TaskDTO.builder()
				.title("Some title")
				.build());

		Assert.assertEquals(task.getStatus(), TaskStatus.PROGRESS);
		updateTaskProgressService.execute(TaskProgressDTO.builder()
				.id(task.getId())
				.progress(100)
				.build());

		final var retriviedTask = retrieveTaskByIdService.execute(task.getId());
		Assert.assertEquals(retriviedTask.getStatus(), TaskStatus.COMPLETE);
	}

}
