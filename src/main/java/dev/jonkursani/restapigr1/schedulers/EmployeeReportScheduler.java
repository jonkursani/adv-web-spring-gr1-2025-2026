package dev.jonkursani.restapigr1.schedulers;

import dev.jonkursani.restapigr1.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j // logging -> printim te mesazheve
public class EmployeeReportScheduler {
    private final EmployeeRepository employeeRepository;

//    @Scheduled(fixedRate = 10000) // 10 seconds
    // Every monday 9 AM
    @Scheduled(cron = "0 0 10 * * FRI")
    public void getAvailabilityReport() {
        log.info("Generating availability report...");

        var employees = employeeRepository.findAll();
        // email
        employees.forEach(emp -> {
            log.info("Employee: {} {}, Available: {}", emp.getFirstName(), emp.getLastName(), true);
        });

        log.info("Report generation complete.");
    }

//   ┌───────────── second (0-59)
//   │ ┌───────────── minute (0 - 59)
//   │ │ ┌───────────── hour (0 - 23)
//   │ │ │ ┌───────────── day of the month (1 - 31)
//   │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
//   │ │ │ │ │ ┌───────────── day of the week (0 - 7)
//   │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
//   │ │ │ │ │ │
//   * * * * * *
}