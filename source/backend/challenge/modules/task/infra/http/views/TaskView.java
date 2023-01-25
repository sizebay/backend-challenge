package backend.challenge.modules.task.infra.http.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskView {

	private String title;
	private String description;

	public TaskView(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
