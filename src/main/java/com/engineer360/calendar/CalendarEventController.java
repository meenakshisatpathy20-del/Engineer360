package com.engineer360.calendar;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class CalendarEventController {

    private final CalendarEventService eventService;

    public CalendarEventController(
            CalendarEventService eventService
    ) {
        this.eventService = eventService;
    }

    @PostMapping
    public CalendarEvent createEvent(
            @RequestBody CalendarEventRequest request,
            Authentication authentication
    ) {

        return eventService.createEvent(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<CalendarEvent> getEvents(
            Authentication authentication
    ) {

        return eventService.getEvents(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    public CalendarEvent getEvent(
            @PathVariable Long id,
            Authentication authentication
    ) {

        return eventService.getEvent(
                id,
                authentication.getName()
        );
    }

    @PutMapping("/{id}")
    public CalendarEvent updateEvent(
            @PathVariable Long id,
            @RequestBody CalendarEventRequest request,
            Authentication authentication
    ) {

        return eventService.updateEvent(
                id,
                request,
                authentication.getName()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(
            @PathVariable Long id,
            Authentication authentication
    ) {

        eventService.deleteEvent(
                id,
                authentication.getName()
        );
    }

    @PostMapping("/sync-contests")
    public List<CalendarEvent> syncContests(
            Authentication authentication
    ) {

        return eventService.syncContests(
                authentication.getName()
        );
    }
}