package nz.com.solnet.taskapi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nz.com.solnet.taskapi.api.domain.entity.TaskEntity;


@Repository
public interface ITaskRepository  extends JpaRepository<TaskEntity, Long> {
	@Query("SELECT t FROM TaskEntity t WHERE t.due_date < CURRENT_DATE ORDER BY t.due_date")
	List<TaskEntity> fetchAllOverdue();	
}

