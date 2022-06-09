package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

@Singleton
public class TaskRepository implements ITaskRepository {

    private static final List<Task> tasks = new ArrayList<>();

    @Override
    public Task index(final Long taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId))
                return task;
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

        task.setId(generateUniqueId());
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
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId())) {
                tasks.get(i).setTitle(task.getTitle());
                tasks.get(i).setDescription(task.getDescription());
            }
        }
        return task;
    }

    @Override
    public void delete(final Long taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }

    @Override
    public Task updateTaskProgress(TaskProgressDTO taskProgressDTO) {

        Task task = index(taskProgressDTO.getId());
        if (task != null) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId().equals(task.getId())) {
                    tasks.get(i).setProgress(taskProgressDTO.getProgress());
                    if (tasks.get(i).getProgress() == 100) {
                        tasks.get(i).setStatus(TaskStatus.COMPLETE);
                        return tasks.get(i);
                    }
                    return tasks.get(i);
                }
            }
        }
        return task;
    }

    private Long generateUniqueId() {

        long id = -1;
        do {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            id = bi.longValue();
        } while (id < 0);

        return id;
    }
}
