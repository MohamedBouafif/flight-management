package Reservation;

import java.util.Date;  

public record Schedule(Date date, String time) {
    
    public Schedule withDate(Date newDate) {
        return new Schedule(newDate, this.time);
    }

    public Schedule withTime(String newTime) {
        return new Schedule(this.date, newTime);
    }
    
    public String formatSchedule() {
        return "Date: " + date + ", Time: " + time;
    }
}
