package ch.gibmit.m226.todo.gui.guiCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

/**
 * @author Damian Zender
 * This class paints the month view component of the calendar
 */
public class GuiCalendarMonthComp extends JComponent {

    private static final String[] WEEKDAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private Calendar cal;
    private List<Rectangle> days;
    private Graphics2D g2d;

    public GuiCalendarMonthComp(Calendar cal) {
        this.cal = cal;
        days = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth()-2;
        int height = getHeight()-2 ;
        int columns = 7;
        int rows = 6;
        int dayWidth = width/columns;
        int dayLabelHeight = 30;
        int weekHeight = (height-dayLabelHeight)/6;

        /**
         * Graphics2D object for antialiasing
         */
        g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);

        /**
         * Background
         */
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);

        /**
         * create day cells
         */
        days.clear();

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < columns; col++){
                Rectangle day = new Rectangle(col * dayWidth, (row * weekHeight)+dayLabelHeight, dayWidth, weekHeight);
                days.add(day);
            }
        }


        /**
         * draw date numbers
         */

        Calendar thisMonth = (Calendar) cal.clone();
        thisMonth.set(Calendar.DAY_OF_MONTH, 1);
        int index = 0;

        /**
         * Test if  the first day of the month is monday, that it doesn't need to generate a previous month object
         */
        if (thisMonth.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            Calendar prevMonth = (Calendar) cal.clone();
            prevMonth.add(Calendar.MONTH, -1);
            /**
             * Test if the first day of the month is sunday, special handling needed because of american week form
             */
            if (thisMonth.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                prevMonth.set(Calendar.DAY_OF_MONTH, prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH)-5);
            }
            else {
                prevMonth.set(Calendar.DAY_OF_MONTH, prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH) - thisMonth.get(Calendar.DAY_OF_WEEK) + 3);
            }

            /**
             * previous month date numbers
             */

            for (int i = prevMonth.get(Calendar.DAY_OF_MONTH); i<=prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                checkForWeekend(index);
                g.setColor(Color.decode("#BDBDBD"));
                g.drawString(String.valueOf(i), (int) Math.round(days.get(index).getX())+5, (int) Math.round(days.get(index).getY())+17);
                index++;
            }
        }

        /**
         * selected month date numbers
         */
        Calendar today = Calendar.getInstance(Locale.GERMANY);

        for (int i = thisMonth.get(Calendar.DAY_OF_MONTH); i<= thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            g.setColor(Color.BLACK);
            checkForWeekend(index);
            /**
             * color today
             */
            if (today.get(Calendar.YEAR) == thisMonth.get(Calendar.YEAR) && today.get(Calendar.MONTH) == thisMonth.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) == i) {
                g.setColor(Color.decode("#DD5238"));
                g2d.fill(new Ellipse2D.Double(days.get(index).getX()+3, days.get(index).getY()+3, 20, 20));
                g.setColor(Color.WHITE);
            }

            g.drawString(String.valueOf(i), (int) Math.round(days.get(index).getX())+5, (int) Math.round(days.get(index).getY())+17);
            index++;
        }

        /**
         * next month date numbers
         */

        Calendar nextMonth = (Calendar) cal.clone();
        nextMonth.add(Calendar.MONTH, 1);
        nextMonth.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = nextMonth.get(Calendar.DAY_OF_MONTH); index<days.size(); i++) {
            checkForWeekend(index);
            g.setColor(Color.decode("#BDBDBD"));
            g.drawString(String.valueOf(i), (int) Math.round(days.get(index).getX())+5, (int) Math.round(days.get(index).getY())+17);
            index++;
        }
        g.setColor(Color.BLACK);

        /**
         * Week day labels
         */
        for (int i = 0; i<WEEKDAYS.length; i++) {
            g2d.drawString(WEEKDAYS[i], (dayWidth*i)+(dayWidth/8), (dayLabelHeight/2)+dayLabelHeight/4);
        }

        /**
         * draw day cells.
         * This needs to be done at the end, so that the backgrounds can't be painted over it.
         */
        days.forEach(g2d::draw);
    }

    /**
     * Checks, if the specified index is at the position of a weekend (Saturday or Sunday). Colors the background
     * and the date label accordingly.
     * @param index the index of the day cell to check
     */
    private void checkForWeekend(int index) {
        if ((index+1)%7==0 || (index+2)%7==0) {
            g2d.setColor(Color.decode("#F5F5F5"));
            g2d.fill(days.get(index));
            g2d.setColor(Color.decode("#878787"));
        }
    }
}