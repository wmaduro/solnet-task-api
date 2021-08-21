package nz.com.solnet.taskapi.usecase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.com.solnet.taskapi.core.exception.custom.CustomResourceNotFoundException;
import nz.com.solnet.taskapi.usecase.domain.entity.TaskEntity;
import nz.com.solnet.taskapi.usecase.repository.ITaskRepository;

@Service
public class TaskService {

	@Autowired
	private ITaskRepository taskRepository;
	
	public List<TaskEntity> fetchAll() {
		return taskRepository.findAll();
	}
	
	public TaskEntity save(TaskEntity taskEntity) {
		return taskRepository.save(taskEntity);
	}

	public TaskEntity findById(Long id) {
		Optional<TaskEntity> oTastEntity = taskRepository.findById(id);
		
		if (!oTastEntity.isPresent()) {
			throw new CustomResourceNotFoundException("Task not found");
		}
		
		return oTastEntity.get();
	}

	public void delete(TaskEntity taskEntity) {
		taskRepository.delete(taskEntity);
	}

	public List<TaskEntity> fetchAllOverdue() {
		return taskRepository.fetchAllOverdue();
	}

}
