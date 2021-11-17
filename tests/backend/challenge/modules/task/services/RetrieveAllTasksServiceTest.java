package backend.challenge.modules.task.services;

import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
					  retornado um array com todas as tarefas que foram criadas até o momento.
		*/
	}

}