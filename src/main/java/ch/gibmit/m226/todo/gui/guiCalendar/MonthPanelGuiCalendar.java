package ch.gibmit.m226.todo.gui.guiCalendar;

import ch.gibmit.m226.todo.gui.guiToDoImpl.ToDoModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Damian Zehnder
 * This class implements the calendar view of the month
 */
public class MonthPanelGuiCalendar extends AbstrGuiCalendar {

    private JToolBar tlBrCalMonth;
    private GuiCalendarMonthComp monthComp;
    private JPanel pnlTools;
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
    private Calendar cal = getCal();
    private JLabel lblMonth;

    /**
     * In this constructor, the panel and the component of the monthly view gets initialized.
     * @param toDoModel the todolist-model, containing all todos
     * @param tbdPnCalendars the tabbed pane of the parent component. It is used to switch the selected tab.
     */
    public MonthPanelGuiCalendar(ToDoModel toDoModel, JTabbedPane tbdPnCalendars) {
        this.setLayout(new BorderLayout());
        pnlTools = new JPanel(new BorderLayout());

        tlBrCalMonth = new JToolBar();
        monthComp = new GuiCalendarMonthComp(toDoModel, tbdPnCalendars);
        addButtonsToToolBar(tlBrCalMonth);
        lblMonth = new JLabel();
        lblMonth.setBorder(new EmptyBorder(5, 10, 5, 10));
        updateDateLabel();


        pnlTools.add(tlBrCalMonth, BorderLayout.LINE_START);
        pnlTools.add(lblMonth, BorderLayout.LINE_END);

        this.add(pnlTools, BorderLayout.PAGE_START);
        this.add(monthComp, BorderLayout.CENTER);
    }

    /**
     * This method catches the action event of the tool bar buttons
     * @param e Action Event of the tool bar buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "back":
                cal.add(Calendar.MONTH, -1);
                updateDateLabel();
                break;

            case "forward":
                cal.add(Calendar.MONTH, 1);
                updateDateLabel();
                break;

            case "today":
                cal.setTime(new Date());
                updateDateLabel();
                break;
        }
    }

    /**
     * updates the date label of the month and repaints the month component
     */
    public void updateDateLabel() {
        lblMonth.setText(sdf.format(cal.getTime()));
        monthComp.repaint();
    }
}
