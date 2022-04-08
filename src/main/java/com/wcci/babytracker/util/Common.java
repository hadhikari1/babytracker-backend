package com.wcci.babytracker.util;

import com.wcci.babytracker.util.modal.TimeDuration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Common {
    /**
     * user passes the date time and we intercept the date time as our need
     * @param localDateTime (user input from form)
     * @return TimeDuration
     */
    public TimeDuration timeDurationFromNow(LocalDateTime localDateTime) {
        Duration duration = Duration.between(LocalDateTime.now(), localDateTime);
        long pastHour = -duration.toHours();
        long pastMin = (-duration.toMinutes() % 60);
        TimeDuration timeDuration = new TimeDuration();
        timeDuration.setDuration(duration);
        timeDuration.setHour(pastHour);
        timeDuration.setMin(pastMin);
        timeDuration.setLocalDateTime(localDateTime);
        if (pastHour > 0 && pastMin > 0) {
            timeDuration.setTimeDifference(String.format("%s hr %s min ago",pastHour, pastMin));
        } else if (pastHour == 0 && pastMin > 0) {
            timeDuration.setTimeDifference(String.format("%s min ago", pastMin));
        } else if (pastHour > 0 && pastMin == 0) {
            timeDuration.setTimeDifference(String.format("%s hr ago",pastHour));
        }
        return timeDuration;
    }
}
