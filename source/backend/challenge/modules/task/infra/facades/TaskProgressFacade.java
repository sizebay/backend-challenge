package backend.challenge.modules.task.infra.facades;

import backend.challenge.modules.task.core.dtos.TaskProgressDTO;
import backend.challenge.modules.task.core.exceptions.InvalidUpdateTaskException;
import backend.challenge.modules.task.core.exceptions.NotFoundTaskException;
import backend.challenge.modules.task.core.services.IUpdateTaskProgressService;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class TaskProgressFacade implements ITaskProgressFacade {

    private final IUpdateTaskProgressService updateTaskProgressService;

    @Inject
    public TaskProgressFacade(final IUpdateTaskProgressService updateTaskProgressService) {
        this.updateTaskProgressService = updateTaskProgressService;
    }


    @Override
    public void update(UUID taskId, TaskProgressView taskProgressView) throws NotFoundTaskException, InvalidUpdateTaskException {
        updateTaskProgressService.execute(TaskProgressDTO.builder()
                .id(taskId)
                .progress(taskProgressView.getProgress())
                .build());
    }

}
