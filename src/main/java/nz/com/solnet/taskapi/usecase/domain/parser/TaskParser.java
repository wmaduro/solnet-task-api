package nz.com.solnet.taskapi.usecase.domain.parser;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nz.com.solnet.taskapi.usecase.domain.entity.TaskEntity;
import nz.com.solnet.taskapi.usecase.domain.input.TaskListResponse;
import nz.com.solnet.taskapi.usecase.domain.input.TaskRequest;
import nz.com.solnet.taskapi.usecase.domain.model.TaskModel;

@Component
public class TaskParser {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ModelMapper modelMapper = new ModelMapper();
	
	public TaskListResponse toTaskListResponse(List<TaskEntity> taskEntityList) {
		
		List<TaskModel> taskModelList = 
				objectMapper.convertValue(taskEntityList, 
						new TypeReference<List<TaskModel>>() {});
		
		return new TaskListResponse(taskModelList);
	}
	
	public TaskEntity toEntity(TaskRequest taskRequest) {
		return objectMapper.convertValue(taskRequest, TaskEntity.class);
	}
	
	public TaskModel toModel(TaskEntity taskEntity) {
		return objectMapper.convertValue(taskEntity, TaskModel.class);
	}
	
	public void mergeToEntity(TaskRequest taskRequest, TaskEntity taskEntity) {
		modelMapper.map(taskRequest, taskEntity);
	}

}
