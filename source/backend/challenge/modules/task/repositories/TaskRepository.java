package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class TaskRepository implements ITaskRepository {

	private static final List<Task> tasks = new ArrayList<>();

	@Override
	public Task index(final Long taskId) {
		for (Task task : tasks) {
			if (task.getId() == taskId) {
				return task;
			}
		}

		return null;
	}

	@Override
	public List<Task> show() {
		return tasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task task = new Task();
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());
		tasks.add(task);
		return task;
	}

	@Override
	public Task update(final Task task) {
		for (Task taskB : tasks) {
			if (taskB.getId() == task.getId()) {
				taskB.setTitle(task.getTitle());
				taskB.setDescription(task.getDescription());
				return task;
			}
		}
		
		return null;
	}

	@Override
	public Task updateProgress(TaskProgressDTO taskProgressDTO) {
		Task task = index(taskProgressDTO.getId());
		task.setProgress(taskProgressDTO.getProgress());
		return task;
	}

	@Override
	public boolean delete(final Long taskId) {
		for (int i = 0; i < tasks.size(); ++i) {
			if (tasks.get(i).getId() == taskId) {
				tasks.remove(i);
				return true;
			}
		}
		
		return false;
	}

}
