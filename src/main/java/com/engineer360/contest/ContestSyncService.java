package com.engineer360.contest;

import com.engineer360.calendar.CalendarEvent;
import com.engineer360.calendar.CalendarEventRepository;
import com.engineer360.profile.DeveloperProfile;
import com.engineer360.profile.DeveloperProfileRepository;
import com.engineer360.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContestSyncService {

    private final DeveloperProfileRepository profileRepository;
    private final CalendarEventRepository eventRepository;
    private final CodeforcesContestProvider codeforcesProvider;

    public ContestSyncService(
            DeveloperProfileRepository profileRepository,
            CalendarEventRepository eventRepository,
            CodeforcesContestProvider codeforcesProvider
    ) {
        this.profileRepository = profileRepository;
        this.eventRepository = eventRepository;
        this.codeforcesProvider = codeforcesProvider;
    }

    public List<CalendarEvent> syncContests(User user) {

        DeveloperProfile profile =
                profileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Developer profile not found"
                                )
                        );

        List<ContestData> contests = new ArrayList<>();

        if (hasValue(profile.getCodeforcesUsername())) {
            contests.addAll(
                    codeforcesProvider.getUpcomingContests()
            );
        }

        List<CalendarEvent> savedEvents = new ArrayList<>();

        for (ContestData contest : contests) {

            CalendarEvent event =
                    new CalendarEvent(
                            contest.getTitle(),
                            "Upcoming " +
                                    contest.getPlatform() +
                                    " programming contest",
                            "CONTEST",
                            contest.getStartDateTime(),
                            contest.getEndDateTime(),
                            user
                    );

            savedEvents.add(
                    eventRepository.save(event)
            );
        }

        return savedEvents;
    }

    private boolean hasValue(String value) {
        return value != null && !value.isBlank();
    }
}