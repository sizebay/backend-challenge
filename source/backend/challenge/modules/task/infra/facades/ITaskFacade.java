package backend.challenge.modules.task.infra.facades;

import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.models.Task;
import backend.challenge.modules.task.infra.http.views.TaskView;

import java.util.List;
import java.util.UUID;

public interface ITaskFacade {

    List<Task> show();
    Task index(UUID taskId) throws NotFoundTaskException;
    Task create(TaskView task);
    void update(UUID taskId, TaskView task) throws NotFoundTaskException, InvalidUpdateTaskException;
    void delete(UUID taskId) throws NotFoundTaskException;

}
