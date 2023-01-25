package backend.challenge.modules.task.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
public class TaskProgressDTO {

	private Long id;
	private int progress;
	
	public TaskProgressDTO(Long id, int progress) {
		this.id = id;
		this.progress = progress;
	}

	public Long getId() {
		return id;
	}

	public int getProgress() {
		return progress;
	}

}
