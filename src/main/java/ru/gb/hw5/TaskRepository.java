package ru.gb.hw5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByDateAsc();

    List<Task> findAllByStatusIsOrderByDate(String status);
}
