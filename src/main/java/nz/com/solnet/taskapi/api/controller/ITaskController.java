package nz.com.solnet.taskapi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nz.com.solnet.taskapi.api.domain.input.TaskInsertRequest;
import nz.com.solnet.taskapi.api.domain.input.TaskUpdateRequest;

@Api(tags = "Tasks")
public interface ITaskController {

	@ApiOperation("Fetch all Tasks")
	public ResponseEntity<?> fetchAll();
	
	@ApiOperation("Fetch all overdue tasks")
	public ResponseEntity<?> fetchAllOverdue();
	
	@ApiOperation("Fetch data for a single task")
	public ResponseEntity<?> get(
			@PathVariable
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id);
	
	@ApiOperation("Add a new task")
	public ResponseEntity<?> add(
			@RequestBody 
			@ApiParam(value = "Task Json information", required = true)
			TaskInsertRequest taskInsertRequest);
	
	@ApiOperation("Modify a task")
	public ResponseEntity<?> update(
			@PathVariable
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id, 
			@ApiParam(value = "Task Json information", required = true)
			@RequestBody TaskUpdateRequest taskUpdateRequest);

	@ApiOperation("Delete a task")
	public ResponseEntity<?> delete(
			@PathVariable 
			@ApiParam(value = "Task id", example = "1", required = true)
			Long id);



}
