package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import java.util.List;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {

		TaskDTO taskDTO = Utils.newTaskDTO();
		createTaskService.execute(taskDTO);
		List<Task> tasks = retrieveAllTasksService.execute();

		Assert.assertNotNull(tasks.get(0));
	}
}