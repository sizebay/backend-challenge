package backend.challenge.modules.task.services;

import kikaha.core.test.KikahaRunner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskProgressController;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private ICreateTaskService createTaskService;
	private IDeleteTaskService deleteTaskService;
	private IUpdateTaskProgressService updateTaskProgressService;
	private TaskProgressController taskProgressController;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
		taskProgressController = new TaskProgressController(updateTaskProgressService);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		final int progress = 50;
		TaskDTO taskDTO = new TaskDTO("", "");
		Task task = createTaskService.execute(taskDTO);
		TaskProgressDTO taskProgressDTO = new TaskProgressDTO(task.getId(), progress);
		updateTaskProgressService.execute(taskProgressDTO);
		deleteTaskService.execute(task.getId());
		assertEquals(task.getTitle(), "");
		assertEquals(task.getDescription(), "");
		assertEquals(task.getProgress(), progress);
		assertEquals(task.getStatus(), TaskStatus.PROGRESS);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		TaskDTO taskDTO = new TaskDTO("", "");
		Task task = createTaskService.execute(taskDTO);
		TaskProgressView taskProgressView = new TaskProgressView(100);
		taskProgressController.updateProgress(task.getId(), taskProgressView);
		deleteTaskService.execute(task.getId());
		assertEquals(task.getTitle(), "");
		assertEquals(task.getDescription(), "");
		assertEquals(task.getProgress(), 100);
		assertEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

}
