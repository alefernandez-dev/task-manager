package dev.alejandro.taskmanager.task.infrastructure;

import dev.alejandro.taskmanager.task.application.RegisterNewTaskUseCase;
import dev.alejandro.taskmanager.task.application.RetrieveTasksUseCase;
import dev.alejandro.taskmanager.task.domain.Tasks;
import dev.alejandro.taskmanager.task.infrastructure.adapter.repository.TasksAdapter;
import dev.alejandro.taskmanager.task.infrastructure.adapter.repository.jpa.TasksJpa;
import dev.alejandro.taskmanager.user.domain.AvailableEmployeeFilter;
import dev.alejandro.taskmanager.user.domain.EmployeeBusyMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskBeanConfiguration {

    @Bean
    Tasks tasks(TasksJpa tasksJpa) {
        return new TasksAdapter(tasksJpa);
    }

    @Bean
    RegisterNewTaskUseCase registerNewTaskUseCase(Tasks repository, AvailableEmployeeFilter filter, EmployeeBusyMarker busyMarker) {
        return new RegisterNewTaskUseCase(repository, filter, busyMarker);
    }

    @Bean
    RetrieveTasksUseCase retrieveTasksUseCase(Tasks repository) {
        return new RetrieveTasksUseCase(repository);
    }

}
