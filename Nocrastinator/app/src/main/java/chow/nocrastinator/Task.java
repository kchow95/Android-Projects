package chow.nocrastinator;

import java.util.Calendar;

/**
 * Created by kchow95 on 7/29/15.
 */
public class Task {
    private String name, message;
    private Calendar startTime, endTime;
    private int interval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public int getInterval() {
        return interval;
    }

    public void getInterval(int duration) {
        this.interval = duration;
    }
}
