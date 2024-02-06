package ru.gb.hw5;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setStatus("NOT_STARTED");
        task.setDate(new java.util.Date());
        return taskRepository.save(task);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        if (taskToUpdate.isPresent()) {
            taskToUpdate.get().setId(id);
            taskToUpdate.get().setTitle(task.getTitle());
            return Optional.of(taskRepository.save(taskToUpdate.get()));
        }
        return Optional.empty();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksWithStatus(String status) {
        if (status == null || status.isEmpty()) {
            return taskRepository.findAllByOrderByDateAsc();
        }
        return taskRepository.findAllByStatusIsOrderByDate(status);
    }

    public Optional<Task> updateTaskStatus(Long id, String status) {
        Optional<Task> taskToUpdate = taskRepository.findById(id);
        if (taskToUpdate.isPresent()) {
            Task task = taskToUpdate.get();
            task.setStatus(status);
            return Optional.of(taskRepository.save(task));
        }
        return Optional.empty();
    }
}
