package in.ac.skcet.event_manager.time_table;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
public class TimeTableHoursService {
    private TimeTableHoursService(){}
    private static final LocalTime[] PERIOD_START_TIMES = {
            LocalTime.parse("08:45"),
            // period 1
            LocalTime.parse("09:40"),
            // period 2
            LocalTime.parse("10:35"),
            // morning break
            LocalTime.parse("11:00"),
            // period 3
            LocalTime.parse("11:55"),
            // period 4
            LocalTime.parse("12:50"),
            // lunch break
            LocalTime.parse("13:50"),
            // period 5
            LocalTime.parse("14:45"),
            // period 6
            LocalTime.parse("15:35"),
            // period 7
            LocalTime.parse("16:45")
    };

    private static final int[] BREAK_PERIODS = {2, 5}; // Periods that are break

    private static final LocalTime DAY_START_TIME = LocalTime.parse("08:45");
    private static final LocalTime DAY_END_TIME = LocalTime.parse("16:45");

    public static int getPeriodNumber(LocalTime time) {
        log.info(time.toString());

        // Check if time is within clg hours
        if (time.isBefore(DAY_START_TIME) || time.isAfter(DAY_END_TIME)) {
            return 0; // Return 0 for out of clg hours
        }

        for (int i = 0; i < PERIOD_START_TIMES.length - 1; i++) {
            // Check if time is within the period start time and end time
            if (time.isAfter(PERIOD_START_TIMES[i]) && time.isBefore(PERIOD_START_TIMES[i + 1])) {
                if(isBreakPeriod(i)){
                    log.info("---- Break period ----");
                    return 0;
                }
                    return i;
            }
        }

        log.info("Un conventional time");
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

    public  static int convert(int period_index){
        if(isBreakPeriod(period_index))
            return -1;
        period_index++;
        if(period_index <= 2)
            return period_index;
        if(period_index <= 5)
            return period_index - 1;
        return period_index - 3;
    }
}
