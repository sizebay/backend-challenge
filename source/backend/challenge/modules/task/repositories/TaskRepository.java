package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Singleton;

@Singleton
public class TaskRepository implements ITaskRepository
{

	private static final List<Task> tasks = new ArrayList<>();

	@Override
	public Task index(final Long taskId)
	{

		for (Task task : tasks)
		{
			if (task.getId().equals(taskId))
			{
				return task;
			}
		}

		return null;
	}

	@Override
	public List<Task> show()
	{
		return tasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO)
	{

		Task task = new Task();

		task.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());

		tasks.add(task);

		return task;
	}

	//TODO Revisar se recebe uma task com o ID ou só o corpo de uma task
	@Override
	public Task update(final Task taskToUpdate, final Task taskWithNewInfo)
	{

		taskToUpdate.setTitle(taskWithNewInfo.getTitle());
		taskToUpdate.setDescription(taskWithNewInfo.getDescription());

		return taskToUpdate;
	}

	@Override
	public Task updateProgress(TaskProgressDTO taskProgressDTO)
	{
		Task task = index(taskProgressDTO.getId());

		if (task != null)
		{
			if (taskProgressDTO.getProgress() > 0 && taskProgressDTO.getProgress() <= 100)
			{
				task.setProgress(taskProgressDTO.getProgress());
				if (taskProgressDTO.getProgress() == 100)
				{
					task.setStatus(TaskStatus.COMPLETE);
				}
			}
		}

		return task;
	}

	@Override
	public void delete(final Long taskId)
	{
		Task taskToRemove = this.index(taskId);

		tasks.remove(taskToRemove);
	}
}
