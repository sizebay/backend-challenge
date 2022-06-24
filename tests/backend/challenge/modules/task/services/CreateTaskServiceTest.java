package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;
	private UtilTestsTasks utilTestsTasks;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		createTaskService = new CreateTaskService(taskRepository);
		utilTestsTasks = new UtilTestsTasks();
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		TaskDTO taskDTO = utilTestsTasks.createTask();
		assertNotNull(createTaskService.execute(taskDTO));
	}




}