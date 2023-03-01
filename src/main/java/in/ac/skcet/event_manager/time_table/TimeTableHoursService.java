package in.ac.skcet.event_manager.time_table;

import java.time.LocalTime;

public class TimeTableHoursService {
    private TimeTableHoursService(){}
    private static final LocalTime[] PERIOD_START_TIMES = {
            LocalTime.parse("08:45"), // Period 1 start time
            LocalTime.parse("09:40"), // Period 2 start time
            LocalTime.parse("10:35"), // Period 3 start time
            LocalTime.parse("11:00"), // Period 4 start time
            LocalTime.parse("11:55"), // Period 5 start time
            LocalTime.parse("13:50"), // Period 6 start time
            LocalTime.parse("14:45"), // Period 7 start time
            LocalTime.parse("15:35")  // Period 8 start time
    };

    private static final int[] BREAK_PERIODS = {2, 5}; // Periods that have break

    private static final int LUNCH_START_PERIOD = 4;
    private static final int LUNCH_END_PERIOD = 5;

    private static final LocalTime DAY_START_TIME = LocalTime.parse("08:45");
    private static final LocalTime DAY_END_TIME = LocalTime.parse("16:45");

    public static int getPeriodNumber(LocalTime time) {
        // Check if time is within clg hours
        if (time.isBefore(DAY_START_TIME) || time.isAfter(DAY_END_TIME)) {
            return 0; // Return 0 for out of clg hours
        }

        for (int i = 0; i < PERIOD_START_TIMES.length; i++) {
            // Check if time is within the period start time and end time
            if (time.isAfter(PERIOD_START_TIMES[i]) && time.isBefore(getPeriodEndTime(i))) {
                // Check if the period has a break or lunch
                if (isBreakPeriod(i) || isLunchPeriod(i)) {
                    return 0; // Return 0 for break or lunch periods
                } else {
                    return i + 1; // Return period number starting from 1
                }
            }
        }

        return 0; // Return 0 for any other case (should never happen)
    }

    private static boolean isBreakPeriod(int period) {
        for (int breakPeriod : BREAK_PERIODS) {
            if (period == breakPeriod) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLunchPeriod(int period) {
        return period >= LUNCH_START_PERIOD && period <= LUNCH_END_PERIOD;
    }

    private static LocalTime getPeriodEndTime(int period) {
        if (isBreakPeriod(period) || isLunchPeriod(period)) {
            return PERIOD_START_TIMES[period];
        } else if (period == PERIOD_START_TIMES.length - 1) {
            return DAY_END_TIME;
        } else {
            return PERIOD_START_TIMES[period + 1];
        }
    }
}
