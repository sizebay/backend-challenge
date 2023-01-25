package backend.challenge.modules.task.services;


import kikaha.core.test.KikahaRunner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private ICreateTaskService createTaskService;
	private IDeleteTaskService deleteTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		TaskDTO taskDTO = new TaskDTO("", "");
		Task task = createTaskService.execute(taskDTO);
        Task taskRetrieved = retrieveTaskByIdService.execute(task.getId());
		deleteTaskService.execute(task.getId());
        assertEquals(task.getId(), taskRetrieved.getId());
	}

}
