package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private ICreateTaskService createTaskService;
	private IUpdateTaskService updateTaskService;
	private IUpdateTaskProgressService updateTaskProgressService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		int standardProgress = standardTask.getProgress();

		TaskProgressDTO newProgressDTO = Utils.newTaskProgressDTO(standardTask.getId());
		Task taskUpdated = updateTaskProgressService.execute(newProgressDTO);

		Assert.assertEquals(taskUpdated.getId(), standardTask.getId());
		Assert.assertNotEquals(taskUpdated.getProgress(), standardProgress);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);

		TaskDTO newTaskDTO = Utils.newTaskDTOUpdate();
		Task newTask = createTaskService.execute(newTaskDTO);
		newTask.setProgress(85);
		newTask.setStatus(TaskStatus.COMPLETE);

		Task updateTask = updateTaskService.execute(standardTask, newTask);
		Assert.assertEquals(updateTask.getStatus(), TaskStatus.PROGRESS);

		TaskProgressDTO newProgressDTO = Utils.newTaskProgressDTO(standardTask.getId());
		newProgressDTO.setProgress(100);

		Task taskCompleted = updateTaskProgressService.execute(newProgressDTO);
		Assert.assertEquals(taskCompleted.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotUpdateTaskProgressWhenProgressLessThanOneHundred () {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		TaskProgressDTO newProgressDTO = Utils.newTaskProgressDTO(standardTask.getId());

		Task updateTask = updateTaskProgressService.execute(newProgressDTO);

		Assert.assertFalse(updateTask.getProgress() < 0);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred () {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		TaskProgressDTO newProgressDTO = Utils.newTaskProgressDTO(standardTask.getId());
		newProgressDTO.setProgress(400);

		Task updateTask = updateTaskProgressService.execute(newProgressDTO);
		Assert.assertFalse(updateTask.getProgress() > 100);
	}

}
