package nz.com.solnet.taskapi.api.domain.input;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import nz.com.solnet.taskapi.api.domain.entity.StatusEnum;
import nz.com.solnet.taskapi.core.exception.custom.CustomBadRequestException;

@Data
public class TaskUpdateRequest {
	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String description;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date due_date;
	@JsonInclude(Include.NON_NULL)
	private String status;

	public void validate() {
		
		if (title != null && title.trim().isEmpty()) {
			throw new CustomBadRequestException("title cannot be null or empty.");
		}
		
		if (status != null) {
			try {
				StatusEnum.valueOf(status);
			} catch (Exception e) {
				throw new CustomBadRequestException("status must be PENDING or FINISHED.");
			}
		}
	}

}
