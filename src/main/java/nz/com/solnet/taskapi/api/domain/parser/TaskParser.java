package nz.com.solnet.taskapi.api.domain.parser;

import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nz.com.solnet.taskapi.api.domain.entity.TaskEntity;
import nz.com.solnet.taskapi.api.domain.input.TaskListResponse;
import nz.com.solnet.taskapi.api.domain.input.TaskInsertRequest;
import nz.com.solnet.taskapi.api.domain.model.TaskModel;

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
	
	public TaskEntity toEntity(TaskInsertRequest taskInsertRequest) {
		return objectMapper.convertValue(taskInsertRequest, TaskEntity.class);
	}
	
	public TaskModel toModel(TaskEntity taskEntity) {
		return objectMapper.convertValue(taskEntity, TaskModel.class);
	}
	
	public void mergeToEntity(Object from, Object to) {
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(from, to);
	}

}
