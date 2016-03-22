package ch.gibmit.m226.todo.gui.guiCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;


/**
 * @author Damian Zehnder
 * This abstract class generates the navigation buttons and adds them to the calendar
 */
public abstract class GuiCalendarAbstr implements ActionListener {

    private static final String BCKIMGNAME = "backButtonIcon";
    private static final String FWDIMGNAME = "forwardButtonIcon";
    private Calendar cal = Calendar.getInstance(Locale.GERMANY);

    /**
     * This mehtod makes a navigation Button with an arrow
     * @param imgName name of the image
     * @param action action witch can be catched from the action Listener in the main calendar class
     * @return the button with the specified icon
     */
    public JButton navButton(String imgName, String action) {
        String imgLocation = "images/"+imgName+".png";

        JButton btn = new JButton();


        Icon img = new ImageIcon(imgLocation);
        btn.setIcon(img);
        btn.setActionCommand(action);
        btn.addActionListener(this);

        return btn;
    }

    /**
     * This method adds all the necessary buttons to the toolbar
     * @param tlBrCal the toolbar, where the button should be added
     */
    public void addButtonsToToolBar(JToolBar tlBrCal) {
        tlBrCal.setFloatable(false);
        JButton btn;

        btn = navButton(BCKIMGNAME, "back");
        tlBrCal.add(btn);

        btn = new JButton("Today");
        btn.setActionCommand("today");
        btn.addActionListener(this);
        tlBrCal.add(btn);

        btn = navButton(FWDIMGNAME, "forward");
        tlBrCal.add(btn);
    }

    /**
     * This method catches all performed actions in the calendar
     * @param e Action Event
     */
    public abstract void actionPerformed(ActionEvent e);

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }
}