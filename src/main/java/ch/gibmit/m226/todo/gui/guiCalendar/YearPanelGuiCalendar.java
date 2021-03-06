package ch.gibmit.m226.todo.gui.guiCalendar;

import ch.gibmit.m226.todo.gui.guiToDoImpl.ToDoModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Damian Zehnder
 * This class implements the calendar view of the year
 */
public class YearPanelGuiCalendar extends AbstrGuiCalendar {

    private JToolBar tlBrCalYear;
    private GuiCalendarYearComp yearComp;
    private JPanel pnlTools;
    private Calendar cal = getCal();
    private JLabel lblYear;

    /**
     * The constructor initializes the panel and year component of the yearly calendar view
     * @param toDoModel the todolist-model, containing all todos
     * @param tbdPnCalendars
     */
    public YearPanelGuiCalendar(ToDoModel toDoModel, JTabbedPane tbdPnCalendars) {
        this.setLayout(new BorderLayout());
        pnlTools = new JPanel(new BorderLayout());

        tlBrCalYear = new JToolBar();
        yearComp = new GuiCalendarYearComp(toDoModel, tbdPnCalendars);
        addButtonsToToolBar(tlBrCalYear);
        lblYear = new JLabel();
        lblYear.setBorder(new EmptyBorder(5, 10, 5, 10));
        updateDateLabel();

        pnlTools.add(tlBrCalYear, BorderLayout.LINE_START);
        pnlTools.add(lblYear, BorderLayout.LINE_END);

        this.add(pnlTools, BorderLayout.PAGE_START);
        this.add(yearComp, BorderLayout.CENTER);

    }

    /**
     * This method catches the action event of the tool bar buttons
     * @param e Action Event of the tool bar buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "back":
                cal.add(Calendar.YEAR, -1);
                updateDateLabel();
                break;

            case "forward":
                cal.add(Calendar.YEAR, 1);
                updateDateLabel();
                break;

            case "today":
                cal.setTime(new Date());
                updateDateLabel();
                break;
        }
    }

    /**
     * updates the date label of the year and repaints the year-view
     */
    public void updateDateLabel() {
        lblYear.setText(String.valueOf(cal.get(Calendar.YEAR)));
        yearComp.repaint();
    }
}
