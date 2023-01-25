package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private ICreateTaskService createTaskService;
	private IDeleteTaskService deleteTaskService;
	private IRetrieveAllTasksService retrieveAllTasksService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		final int tasksToCreate = 2;

		for (int i = 0; i < tasksToCreate; ++i) {
			TaskDTO taskDTO = new TaskDTO("", "");
			createTaskService.execute(taskDTO);
		}

		List<Task> tasks = retrieveAllTasksService.execute();
		assertEquals(tasks.size(), tasksToCreate);

		for (long i = 0; i < tasksToCreate; ++i) {
			deleteTaskService.execute(i);
		}
	}

}
