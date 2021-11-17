package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TaskRepository implements ITaskRepository {

	@Override
	public Task index(final Long taskId) {
		// TODO: Criar método responsável por retornar tarefa por id

		return null;
	}

	@Override
	public List<Task> show() {
		// TODO: Criar método responsável por retornar todas as tarefas

		return null;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		// TODO: Criar método responsável por criar uma tarefa

		return null;
	}

	@Override
	public Task update(final Task task) {
		// TODO: Criar método responsável por atualizar uma tarefa

		return null;
	}

	@Override
	public void delete(final Long taskId) {
 		// TODO: Criar método responsável por deletar tarefa por id

	}

}
