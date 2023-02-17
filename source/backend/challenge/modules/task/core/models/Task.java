package backend.challenge.modules.task.core.models;

import backend.challenge.modules.task.core.enums.TaskStatus;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Task implements Cloneable {

	@Builder.Default
	private UUID id = UUID.randomUUID();

	private String title;

	private String description;

	@Builder.Default
	private int progress = 0;

	private TaskStatus status;

	@Builder.Default
	private Date createdAt = new Date();

	@Override
	public Task clone() {
		try {
			var clone = (Task) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
