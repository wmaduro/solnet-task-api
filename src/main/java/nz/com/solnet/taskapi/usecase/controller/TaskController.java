package nz.com.solnet.taskapi.usecase.controller;

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
import nz.com.solnet.taskapi.usecase.domain.entity.TaskEntity;
import nz.com.solnet.taskapi.usecase.domain.input.TaskListResponse;
import nz.com.solnet.taskapi.usecase.domain.input.TaskRequest;
import nz.com.solnet.taskapi.usecase.domain.model.TaskModel;
import nz.com.solnet.taskapi.usecase.domain.parser.TaskParser;
import nz.com.solnet.taskapi.usecase.service.TaskService;

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
	public ResponseEntity<?> get(@PathVariable Long id) {
		TaskModel taskModel = taskParser.toModel(taskService.findById(id));
		return new ResponseEntity<>(taskModel, HttpStatus.OK);
	}

	@ApiOperation("Add a new task")
	@PostMapping
	public ResponseEntity<?> add(@RequestBody TaskRequest taskRequest) {

		TaskEntity taskEntity = taskParser.toEntity(taskRequest);
		taskEntity = taskService.save(taskEntity);
		TaskModel taskModel = taskParser.toModel(taskEntity);

		return new ResponseEntity<>(taskModel, HttpStatus.CREATED);
	}

	@ApiOperation("Modify a task")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
		TaskEntity taskEntity = taskService.findById(id);
		taskParser.mergeToEntity(taskRequest, taskEntity);
		taskEntity = taskService.save(taskEntity);
		TaskModel taskModel = taskParser.toModel(taskEntity);
		return new ResponseEntity<>(taskModel, HttpStatus.OK);
	}

	@ApiOperation("Delete a task")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		TaskEntity taskEntity = taskService.findById(id);
		taskService.delete(taskEntity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}



}
