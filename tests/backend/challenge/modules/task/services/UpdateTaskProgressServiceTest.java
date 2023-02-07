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
	private IUpdateTaskProgressService updateTaskProgressService;
	private IUpdateTaskService updateTaskService;

	@Before
	public void init(){
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		int standardProgress = standardTask.getProgress();

		TaskProgressDTO newProgressTDO = Utils.newTaskProgressDTO(standardTask.getId());

		Task taskUpdated = updateTaskProgressService.execute(newProgressTDO);

		Assert.assertEquals(taskUpdated.getId(), standardTask.getId());
		Assert.assertNotEquals(taskUpdated.getProgress(), standardProgress);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);

		TaskDTO newTaskDTO = Utils.newTaskDTOToUpdate();
		Task newTask = createTaskService.execute(newTaskDTO);
		newTask.setProgress(88);
		newTask.setStatus(TaskStatus.COMPLETE);

		Task updatedTask = updateTaskService.execute(standardTask, newTask);
		Assert.assertEquals(updatedTask.getStatus(), TaskStatus.PROGRESS);

		TaskProgressDTO newProgressTDO = Utils.newTaskProgressDTO(standardTask.getId());
		newProgressTDO.setProgress(100);

		Task taskCompleted = updateTaskProgressService.execute(newProgressTDO);

		Assert.assertEquals(taskCompleted.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred(){
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		TaskProgressDTO newProgressTDO = Utils.newTaskProgressDTO(standardTask.getId());
		newProgressTDO.setProgress(-5);

		Task updatedTask = updateTaskProgressService.execute(newProgressTDO);

		Assert.assertFalse(updatedTask.getProgress() < 0);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred(){
		TaskDTO standardTaskDTO = Utils.newTaskDTO();
		Task standardTask = createTaskService.execute(standardTaskDTO);
		TaskProgressDTO newProgressTDO = Utils.newTaskProgressDTO(standardTask.getId());
		newProgressTDO.setProgress(500);

		Task updatedTask = updateTaskProgressService.execute(newProgressTDO);

		Assert.assertFalse(updatedTask.getProgress() > 100);
	}
}
