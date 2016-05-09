package ch.gibmit.m226.todo.dto;

import java.util.Date;

/**
 * @author Damian Zehnder
 */
public class Repeater {

    private Object recurrence;
    private int rate;
    private Object hasEndDate;
    private Date endDate;
    private Boolean[] weekDays;


    public Repeater() {

    }

    public Object getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Object recurrence) {
        this.recurrence = recurrence;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Object getHasEndDate() {
        return hasEndDate;
    }

    public void setHasEndDate(Object hasEndDate) {
        this.hasEndDate = hasEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Boolean[] weekDays) {
        this.weekDays = weekDays;
    }
}