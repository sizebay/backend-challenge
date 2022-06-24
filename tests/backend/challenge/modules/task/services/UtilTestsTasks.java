package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;


import java.util.Random;
import java.util.UUID;

public class UtilTestsTasks {

    public TaskDTO createTask() {
        TaskDTO taskDTO = TaskDTO.create();
        taskDTO.setDescription(randomString());
        taskDTO.setTitle(randomString());

        return taskDTO;
    }

    public TaskView createTaskView() {
        TaskView taskView = new TaskView();
        taskView.setDescription(randomString());
        taskView.setTitle(randomString());

        return taskView;
    }

    public Task taskViewToTask(Long id, Task task) {
        Task t = new Task();
        t.setId(task.getId());
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setStatus(task.getStatus());
        t.setProgress(task.getProgress());
        t.setCreatedAt(task.getCreatedAt());

        return t;
    }

    public String randomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public TaskProgressDTO createTaskProgressDto(Long id) {
        return createTaskProgressDto(id, null);
    }

    public TaskProgressDTO createTaskProgressDto(Long id, Integer progress) {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
        taskProgressDTO.setProgress(progress != null ? progress : randomInt());
        taskProgressDTO.setId(id);

        return taskProgressDTO;
    }

    public int randomInt() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public Task preserveTaskValues(Task task) {
        Task t = new Task();
        t.setId(task.getId());
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setProgress(task.getProgress());
        t.setStatus(task.getStatus());
        t.setCreatedAt(task.getCreatedAt());
        return t;
    }

    public Task changeDescriptionAndTitle(Task task) {
        task.setTitle(randomString());
        task.setDescription(randomString());
        return task;
    }

    public Long getRandomId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
