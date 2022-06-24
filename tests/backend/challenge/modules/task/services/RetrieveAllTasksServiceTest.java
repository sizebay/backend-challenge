package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;
	private UtilTestsTasks utilTestsTasks;
	private CreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		utilTestsTasks = new UtilTestsTasks();
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		int taskToCreate = 3;

		TaskDTO taskDTO = utilTestsTasks.createTask();
		for(var i = 0; i< taskToCreate; i++) {
			createTaskService.execute(taskDTO);
		}

		List<Task> tasks = retrieveAllTasksService.execute();

		assertEquals(3, tasks.size());
	}

}