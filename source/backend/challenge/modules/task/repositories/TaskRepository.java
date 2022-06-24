package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskRepository implements ITaskRepository {

	private static final List<Task> lsTasks = new ArrayList<>();
	@Override
	public Task index(final Long taskId) {
		for (Task task : lsTasks) {
			if (taskId.equals(task.getId())) {
				return task;
			}
		}

		return null;
	}

	@Override
	public List<Task> show() {
		return lsTasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task task = new Task();

		task.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setStatus(TaskStatus.PROGRESS);
		task.setProgress(0);
		task.setCreatedAt(new Date());

		lsTasks.add(task);

		return task;
	}

	@Override
	public Task update(final Task task) {
		lsTasks.forEach(tks -> {
			if (task.getId().equals(tks.getId())) {
				tks.setTitle(task.getTitle());
				tks.setDescription(task.getDescription());
			}
		});

		return task;
	}

	@Override
	public void delete(final Long taskId) {
 		lsTasks.removeIf(tsk -> taskId.equals(tsk.getId()));
	}

	@Override
	public Task updateTaskProgres(TaskProgressDTO taskProgressDTO) {
		for (Task task : lsTasks) {
			if (taskProgressDTO.getId().equals(task.getId())) {
				task.setStatus(taskProgressDTO.getProgress() == 100 ? TaskStatus.COMPLETE:TaskStatus.PROGRESS);
				task.setProgress(taskProgressDTO.getProgress());
				return task;
			}
		}

		return null;
	}
}
