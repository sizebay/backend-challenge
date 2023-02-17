package backend.challenge.modules.task.infra.repositories;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Singleton
public class LocalTaskRepository implements ITaskRepository {

	final Set<Task> memory;

	public LocalTaskRepository() {
		memory = new HashSet<>();
	}

	@Override
	public Task index(final UUID taskId) throws NotFoundTaskException {
		return getTask(taskId);
	}

	@Override
	public List<Task> show() {
		return memory.stream().toList();
	}

	@Override
	public void add(final Task task) {
		memory.add(task);
	}

	@Override
	public Task update(final Task task) throws NotFoundTaskException {
		final var taskInMemory = getTask(task.getId());
		memory.remove(taskInMemory);
		memory.add(task);
		return task;
	}

	@Override
	public void delete(final UUID taskId) throws NotFoundTaskException {
		final var task = getTask(taskId);
		memory.remove(task);
	}

	private Task getTask(UUID taskId) throws NotFoundTaskException {
		final var optional = memory.stream()
		  		.filter(t -> t.getId().equals(taskId))
				.findFirst();

		if (optional.isEmpty())
			throw new NotFoundTaskException(taskId);
		return optional.get();
	}

}
