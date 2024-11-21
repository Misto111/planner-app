package com.plannerapp.repository;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignee(UserEntity userEntity);

    @Query(nativeQuery = true, value = "SELECT  * FROM tasks WHERE assignee_id IS NULL")
    List<Task> getAllAvailableTasks();
}
