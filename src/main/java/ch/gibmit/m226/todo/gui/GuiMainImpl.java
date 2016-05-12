package ch.gibmit.m226.todo.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

//import ch.gibmit.m226.todo.bl.Serializor;
import ch.gibmit.m226.todo.gui.guiCalendar.GuiCalendarImpl;
import ch.gibmit.m226.todo.gui.guiMenu.GuiMenu;
import ch.gibmit.m226.todo.gui.guiToDoImpl.GuiToDoEditCategoriesImpl;
import ch.gibmit.m226.todo.gui.guiToDoImpl.GuiToDoMainImpl;
import ch.gibmit.m226.todo.gui.interfaces.GuiCalendarPanel;

/**
 * @author Colin Herzog This class divides the GUI by its tabs. The left tab is
 *         the detailed view for the ToDos, whilst the right tab shows calendar
 *         view.
 */
public class GuiMainImpl extends JFrame {

	/**
	 * This is the main pane. It's a tabbed pane where you can split a view into
	 * tabs.
	 */
	private JTabbedPane mainPane;
	/**
	 * This is the class for the left tab.
	 */
	private GuiToDoMainImpl gtm;
	private GuiCalendarPanel gtc;
	private GuiMenu gm;
//	private Serializor sr;

	public GuiMainImpl() {

		gm = new GuiMenu();
		mainPane = new JTabbedPane();
		gtm = new GuiToDoMainImpl();
		gtc = new GuiCalendarImpl();
		sr = new Serializor(GuiToDoEditCategoriesImpl.getInstance().getCategoryController().getCategory().getCategoryDAO(), gtm.getToDoController().getToDo().getToDoDAO());
		
		this.mainPane.addTab("ToDos", gtm.getPanel());
		this.mainPane.addTab("Calendar", gtc.getCalendarPanel());
		this.setJMenuBar(gm.getMenu());

		this.add(mainPane);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.pack();
		this.setSize(950, 600);
		this.setMinimumSize(new Dimension(950, 550));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		this.setVisible(true);
		
		this.setActionListeners();

	}

	public void setActionListeners() {
        gm.getSave().addActionListener(e -> {
            gtm.saveChanges();
//            sr.save();
        });
        gm.getNewToDo().addActionListener(e -> {
            gtm.addToDo();
        });
	}
}
