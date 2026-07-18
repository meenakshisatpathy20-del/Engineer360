package com.engineer360.contest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CodeforcesContestProvider implements ContestProvider {

    private final RestClient restClient;

    public CodeforcesContestProvider() {
        this.restClient = RestClient.builder()
                .baseUrl("https://codeforces.com/api")
                .build();
    }

    @Override
    public List<ContestData> getUpcomingContests() {

        Map response = restClient.get()
                .uri("/contest.list")
                .retrieve()
                .body(Map.class);

        List<ContestData> contests = new ArrayList<>();

        if (response == null) {
            return contests;
        }

        List<Map<String, Object>> result =
                (List<Map<String, Object>>) response.get("result");

        if (result == null) {
            return contests;
        }

        for (Map<String, Object> contest : result) {

            String phase = (String) contest.get("phase");

            if (!"BEFORE".equals(phase)) {
                continue;
            }

            String name = (String) contest.get("name");

            Number startSeconds =
                    (Number) contest.get("startTimeSeconds");

            Number durationSeconds =
                    (Number) contest.get("durationSeconds");

            if (startSeconds == null || durationSeconds == null) {
                continue;
            }

            LocalDateTime startDateTime =
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(startSeconds.longValue()),
                            ZoneId.systemDefault()
                    );

            LocalDateTime endDateTime =
                    startDateTime.plusSeconds(durationSeconds.longValue());

            contests.add(
                    new ContestData(
                            name,
                            "Codeforces",
                            startDateTime,
                            endDateTime
                    )
            );
        }

        return contests;
    }
}