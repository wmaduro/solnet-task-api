package nz.com.solnet.taskapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nz.com.solnet.taskapi.api.domain.entity.TaskEntity;
import nz.com.solnet.taskapi.api.domain.input.TaskInsertRequest;
import nz.com.solnet.taskapi.api.domain.input.TaskListResponse;
import nz.com.solnet.taskapi.api.domain.input.TaskUpdateRequest;
import nz.com.solnet.taskapi.api.domain.model.TaskModel;
import nz.com.solnet.taskapi.api.domain.parser.TaskParser;
import nz.com.solnet.taskapi.api.service.TaskService;

@Api(tags = "Tasks")
@RestController
@RequestMapping(path = "/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskParser taskParser;

	@ApiOperation("Fetch all Tasks")
	@GetMapping(value = "/fetch-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchAll() {

		List<TaskEntity> taskEntityList = taskService.fetchAll();
		TaskListResponse taskListResponse = taskParser.toTaskListResponse(taskEntityList);

		return new ResponseEntity<>(taskListResponse, HttpStatus.OK);
	}
	
	@ApiOperation("Fetch all overdue tasks")
	@GetMapping(value = "/fetch-all-overdue", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchAllOverdue() {

		List<TaskEntity> taskEntityList = taskService.fetchAllOverdue();
		TaskListResponse taskListResponse = taskParser.toTaskListResponse(taskEntityList);

		return new ResponseEntity<>(taskListResponse, HttpStatus.OK);
	}
	
	@ApiOperation("Fetch data for a single task")
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id) {
		TaskModel taskModel = taskParser.toModel(taskService.findById(id));
		return new ResponseEntity<>(taskModel, HttpStatus.OK);
	}

	@ApiOperation("Add a new task")
	@PostMapping
	public ResponseEntity<?> add(@RequestBody 
			@ApiParam(value = "Task Json information", required = true)
			TaskInsertRequest taskInsertRequest) {
		
		taskInsertRequest.validate();

		TaskEntity taskEntity = taskParser.toEntity(taskInsertRequest);
		taskEntity = taskService.save(taskEntity);
		TaskModel taskModel = taskParser.toModel(taskEntity);

		return new ResponseEntity<>(taskModel, HttpStatus.CREATED);
	}

	@ApiOperation("Modify a task")
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id, 
			@ApiParam(value = "Task Json information", required = true)
			@RequestBody TaskUpdateRequest taskUpdateRequest) {
		
		taskUpdateRequest.validate();
		
		TaskEntity taskEntity = taskService.findById(id);
		taskParser.mergeToEntity(taskUpdateRequest, taskEntity);
		taskEntity = taskService.save(taskEntity);
		TaskModel taskModel = taskParser.toModel(taskEntity);
		return new ResponseEntity<>(taskModel, HttpStatus.OK);
	}

	@ApiOperation("Delete a task")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable 
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id) {
		TaskEntity taskEntity = taskService.findById(id);
		taskService.delete(taskEntity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}



}
