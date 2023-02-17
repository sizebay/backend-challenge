package backend.challenge.modules.task.infra.facades;

import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;

import java.util.UUID;

public interface ITaskProgressFacade {

    void update(UUID taskId, TaskProgressView taskProgressView) throws NotFoundTaskException, InvalidUpdateTaskException;

}
