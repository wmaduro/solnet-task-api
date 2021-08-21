package nz.com.solnet.taskapi.usecase.domain.input;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import nz.com.solnet.taskapi.usecase.domain.model.TaskModel;

@Data
@AllArgsConstructor
public class TaskListResponse {
	private List<TaskModel> tasks =  new ArrayList<>();

}
