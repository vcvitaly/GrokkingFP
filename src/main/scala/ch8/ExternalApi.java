package io.github.vcvitaly.grokkingfp.ch8;

import java.util.List;
import java.util.Random;

/**
 * ExternalApi.
 *
 * @author Vitalii Chura
 */
public final class ExternalApi {

    private ExternalApi() {
    }

    static void createMeetingApiCall(List<String> names, MeetingTime meetingTime) {
        Random rand = new Random();
        if (rand.nextFloat() < 0.25) {
            throw new RuntimeException("OOPS");
        }
        System.out.println(
                String.format("Created a meeting for %s: %s", names, meetingTime)
        );
    }

    static List<MeetingTime> calendarEntriesApiCall(String name) {
        Random rand = new Random();
        if (rand.nextFloat() < 0.25) {
            throw new RuntimeException("Connection error");
        }
        if (name.equals("Alice")) {
            return List.of(new MeetingTime(8, 10), new MeetingTime(11, 12));
        } else if (name.equals("Bob")) {
            return List.of(new MeetingTime(9, 10));
        } else {
            return List.of(new MeetingTime(rand.nextInt(5) + 8, rand.nextInt(4) + 13));
        }
    }
}
