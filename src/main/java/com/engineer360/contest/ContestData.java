package com.engineer360.contest;

import java.time.LocalDateTime;

public class ContestData {

    private String title;
    private String platform;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public ContestData(
            String title,
            String platform,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    ) {
        this.title = title;
        this.platform = platform;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}