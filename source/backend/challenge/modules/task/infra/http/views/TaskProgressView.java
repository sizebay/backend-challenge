package backend.challenge.modules.task.infra.http.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskProgressView {

	private int progress;

	public TaskProgressView(int progress) {
		this.progress = progress;
	}

	public int getProgress() {
		return progress;
	}

}
