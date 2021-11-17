package backend.challenge.modules.task.services;

import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private IDeleteTaskService deleteTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		deleteTaskService = new DeleteTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() {
		// TODO: Para que esse teste passe, sua aplicação deve permitir que tarefas sejam deletadas por id.
	}




}