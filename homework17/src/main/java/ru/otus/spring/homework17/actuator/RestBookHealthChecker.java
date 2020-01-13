package ru.otus.spring.homework17.actuator;

import lombok.Data;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework17.rest.RestControllerForBook;

@Component
@Data
public class RestBookHealthChecker implements HealthIndicator {

    private final RestControllerForBook restControllerForBook;

    @Override
    public Health health() {
        if (restControllerForBook != null) {
            return Health.up().withDetail("message", "Rest for book is up").build();
        } else {
            return Health.down().withDetail("message", "Rest for book is down").build();
        }
    }
}
