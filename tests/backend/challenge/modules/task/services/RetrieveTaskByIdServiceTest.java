package backend.challenge.modules.task.services;


import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.IRetrieveTaskByIdService;
import backend.challenge.modules.task.infra.repositories.LocalTaskRepository;
import backend.challenge.modules.task.infra.services.RetrieveTaskByIdService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private ITaskRepository taskRepository;
	private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
	public void init() {
		taskRepository = new LocalTaskRepository();
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		final var uuid = UUID.fromString("01617e15-c5da-4229-9e63-9516e73f7bfe");
		taskRepository.add(Task.builder()
				.id(uuid)
				.build());

		try {
			final var task = retrieveTaskByIdService.execute(uuid);
			Assert.assertNotNull(task);
		} catch (NotFoundTaskException e) {
			Assert.fail();
		}
	}

}
