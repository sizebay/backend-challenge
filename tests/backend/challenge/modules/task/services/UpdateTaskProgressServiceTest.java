package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(KikahaRunner.class)
public class UpdateTaskProgressServiceTest {

    private UtilTestsTasks utilTestsTasks;
    private ICreateTaskService createTaskService;
    private IUpdateTaskProgressService updateTaskProgresService;
    private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
    public void init() {
        final ITaskRepository taskRepository = new TaskRepository();
        utilTestsTasks = new UtilTestsTasks();
        createTaskService = new CreateTaskService(taskRepository);
        updateTaskProgresService = new UpdateTaskProgressService(taskRepository);
        retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
    }

    @Test
    public void shouldBeAbleToUpdateTaskProgress() {
        TaskDTO taskDTO = utilTestsTasks.createTask();
        Task task = createTaskService.execute(taskDTO);

		Task taskTest = utilTestsTasks.preserveTaskValues(task);

        TaskProgressDTO taskProgressDTO = utilTestsTasks.createTaskProgressDto(task.getId());
        updateTaskProgresService.execute(taskProgressDTO);

        Task result = retrieveTaskByIdService.execute(task.getId());

        assertEquals(result.getId(), taskTest.getId());
        assertEquals(result.getTitle(), taskTest.getTitle());
        assertEquals(result.getDescription(), taskTest.getDescription());
        assertNotEquals(result.getProgress(), taskTest.getProgress());
        assertEquals(result.getStatus(), taskTest.getStatus());
        assertEquals(result.getCreatedAt(), taskTest.getCreatedAt());

    }

    @Test
    public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
        TaskDTO taskDTO = utilTestsTasks.createTask();
        Task task = createTaskService.execute(taskDTO);

        Task taskTest = utilTestsTasks.preserveTaskValues(task);

        TaskProgressDTO taskProgressDTO = utilTestsTasks.createTaskProgressDto(task.getId(), 100);
        updateTaskProgresService.execute(taskProgressDTO);

        Task result = retrieveTaskByIdService.execute(task.getId());

        assertEquals(result.getId(), taskTest.getId());
        assertEquals(result.getTitle(), taskTest.getTitle());
        assertEquals(result.getDescription(), taskTest.getDescription());
        assertNotEquals(result.getProgress(), taskTest.getProgress());
        assertNotEquals(result.getStatus(), taskTest.getStatus());
        assertEquals(result.getCreatedAt(), taskTest.getCreatedAt());
    }

}
