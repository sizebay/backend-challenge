package backend.challenge.modules.task.infra.services;

import backend.challenge.modules.task.core.dtos.TaskDTO;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.core.repositories.ITaskRepository;
import backend.challenge.modules.task.core.services.ICreateTaskService;
import backend.challenge.modules.task.core.services.ITaskLevelerService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTaskService implements ICreateTaskService {

	private final ITaskRepository taskRepository;
	private final ITaskLevelerService taskLeveler;

	@Inject
	public CreateTaskService(final ITaskRepository taskRepository, final ITaskLevelerService taskLeveler) {
		this.taskRepository = taskRepository;
		this.taskLeveler = taskLeveler;
	}

	@Override
	public Task execute(TaskDTO taskDTO) {
		final var task = Task.builder()

				.title(taskDTO.getTitle())
				.description(taskDTO.getDescription())
				.build();

		taskLeveler.execute(task);
		taskRepository.add(task);
		return task;
	}

}
