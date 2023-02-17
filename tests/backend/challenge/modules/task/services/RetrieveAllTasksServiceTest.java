package backend.challenge.modules.task.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.IRetrieveAllTasksService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.CreateTaskService;
import backend.challenge.modules.task.infra.services.RetrieveAllTasksService;
import backend.challenge.modules.task.infra.services.TaskLevelerService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private ITaskRepository taskRepository;
	private ICreateTaskService createTaskService;
	private IRetrieveAllTasksService retrieveAllTasksService;

	@Before
	public void init() {
		final var taskLeveler = new TaskLevelerService();

		taskRepository = new LocalTaskRepository();
		createTaskService = new CreateTaskService(taskRepository, taskLeveler);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		createTaskService.execute(TaskDTO.builder()
				.title("Task 1")
				.build());
		createTaskService.execute(TaskDTO.builder()
				.title("Task 2")
				.build());

		final var tasks = retrieveAllTasksService.execute();
		Assert.assertEquals(tasks.size(), 2);
	}

}