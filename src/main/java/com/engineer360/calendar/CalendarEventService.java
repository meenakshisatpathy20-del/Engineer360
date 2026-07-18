package com.engineer360.calendar;

import com.engineer360.contest.ContestSyncService;
import com.engineer360.user.User;
import com.engineer360.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarEventService {

    private final CalendarEventRepository eventRepository;
    private final UserRepository userRepository;
    private final ContestSyncService contestSyncService;

    public CalendarEventService(
            CalendarEventRepository eventRepository,
            UserRepository userRepository,
            ContestSyncService contestSyncService
    ) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.contestSyncService = contestSyncService;
    }

    private User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found"
                        )
                );
    }

    public CalendarEvent createEvent(
            CalendarEventRequest request,
            String email
    ) {

        User user = getUser(email);

        CalendarEvent event = new CalendarEvent(
                request.getTitle(),
                request.getDescription(),
                request.getEventType(),
                request.getStartDateTime(),
                request.getEndDateTime(),
                user
        );

        return eventRepository.save(event);
    }

    public List<CalendarEvent> getEvents(String email) {

        User user = getUser(email);

        return eventRepository.findByUser(user);
    }

    public CalendarEvent getEvent(
            Long id,
            String email
    ) {

        User user = getUser(email);

        return eventRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Event not found"
                        )
                );
    }

    public CalendarEvent updateEvent(
            Long id,
            CalendarEventRequest request,
            String email
    ) {

        CalendarEvent event =
                getEvent(id, email);

        event.setTitle(
                request.getTitle()
        );

        event.setDescription(
                request.getDescription()
        );

        event.setEventType(
                request.getEventType()
        );

        event.setStartDateTime(
                request.getStartDateTime()
        );

        event.setEndDateTime(
                request.getEndDateTime()
        );

        return eventRepository.save(event);
    }

    public void deleteEvent(
            Long id,
            String email
    ) {

        CalendarEvent event =
                getEvent(id, email);

        eventRepository.delete(event);
    }

    public List<CalendarEvent> syncContests(
            String email
    ) {

        User user = getUser(email);

        return contestSyncService.syncContests(user);
    }
}