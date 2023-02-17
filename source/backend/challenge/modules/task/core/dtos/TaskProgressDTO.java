package backend.challenge.modules.task.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Builder
@Accessors(chain = true)
public class TaskProgressDTO {

	private UUID id;
	private int progress;

}
