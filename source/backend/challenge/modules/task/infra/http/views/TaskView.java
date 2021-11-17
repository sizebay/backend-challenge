package backend.challenge.modules.task.infra.http.views;

import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskView {

	private String title;
	private String description;

}
