package backend.challenge.modules.task.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.ITaskLevelerService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.CreateTaskService;
import backend.challenge.modules.task.infra.services.TaskLevelerService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new LocalTaskRepository();
		final ITaskLevelerService taskLeveler = new TaskLevelerService();

		createTaskService = new CreateTaskService(taskRepository, taskLeveler);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		final var dto = TaskDTO.builder()
				.title("Some title")
				.description("Some description")
				.build();

		final var task = createTaskService.execute(dto);
		Assert.assertEquals(dto.getTitle(), task.getTitle());
		Assert.assertEquals(dto.getDescription(), task.getDescription());
	}
}