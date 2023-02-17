package backend.challenge.modules.task.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.IDeleteTaskService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.CreateTaskService;
import backend.challenge.modules.task.infra.services.DeleteTaskService;
import backend.challenge.modules.task.infra.services.TaskLevelerService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private ITaskRepository taskRepository;
	private IDeleteTaskService deleteTaskService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final var taskLeveler = new TaskLevelerService();

		taskRepository = new LocalTaskRepository();
		createTaskService = new CreateTaskService(taskRepository, taskLeveler);
		deleteTaskService = new DeleteTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() throws NotFoundTaskException {
		final var task1 = createTaskService.execute(TaskDTO.builder()
				.title("Task 1")
				.description("Some description")
				.build());

		final var task2 = createTaskService.execute(TaskDTO.builder()
				.title("Task 2")
				.description("Some description")
				.build());

		Assert.assertEquals(taskRepository.show().size(), 2);

		deleteTaskService.execute(task1.getId());
		Assert.assertEquals(taskRepository.show().size(), 1);
		Assert.assertEquals(taskRepository.show().stream()
				.findFirst()
				.get()
				.getId(), task2.getId());
	}




}