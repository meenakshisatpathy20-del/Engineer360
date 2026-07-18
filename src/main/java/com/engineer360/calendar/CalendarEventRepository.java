package com.engineer360.calendar;

import com.engineer360.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarEventRepository
        extends JpaRepository<CalendarEvent, Long> {

    List<CalendarEvent> findByUser(User user);

    Optional<CalendarEvent> findByIdAndUser(
            Long id,
            User user
    );
}