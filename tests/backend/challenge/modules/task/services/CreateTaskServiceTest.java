package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;
	private IDeleteTaskService deleteTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		TaskDTO taskDTO = new TaskDTO("", "");
		Task task = createTaskService.execute(taskDTO);
        deleteTaskService.execute(task.getId());
		assertNotNull(task);
	}

}
