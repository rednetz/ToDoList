package ch.gibmit.m226.todo.gui.guiToDoImpl;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import ch.gibmit.m226.todo.dto.ToDoDTO;

/**
 * Created by colin on 24.02.16.
 */
public class GuiToDoMainImpl {

	private JPanel pnlToDoMain;
	private JSplitPane sptPnToDoMain;
	private GuiToDoLeftImpl gtl;
	private GuiToDoRightImpl gtr;

	private ToDoController toDoController;
	private ToDoModel toDoModel;

	private int lastIndex;

	public GuiToDoMainImpl() {

		this.toDoModel = new ToDoModel();
		this.toDoController = new ToDoController(toDoModel);

		this.gtl = new GuiToDoLeftImpl(toDoModel, toDoController);

		this.gtr = new GuiToDoRightImpl(toDoModel, toDoController);

		this.gtr.disableAll();

		this.setUpPanels();

		this.setUpComponents();

		this.placeComponents();

	}

	private void setUpPanels() {
		this.pnlToDoMain = new JPanel(new BorderLayout());
	}

	private void setUpComponents() {
		this.sptPnToDoMain = new JSplitPane();
		this.sptPnToDoMain.setDividerLocation(0.3);

		this.gtl.getBtnAddToDo().addActionListener(e -> {
			this.addToDo();
		});

		this.gtl.getLstToDos().addListSelectionListener(e -> {
			if (this.gtl.getLstToDos().getSelectedIndex() >= 0) {
				gtl.getBtnRemoveToDo().setEnabled(true);
				gtr.enableAll();
				int selected = gtl.getLstToDos().getSelectedIndex();
				gtr.updateValues(this.toDoController.getSingleToDo(selected));
				//this.saveChangesByIndex(gtr.getChangedToDo(), lastIndex);
				//this.lastIndex = this.gtl.getLstToDos().getSelectedIndex();
			} else {
				gtl.getBtnRemoveToDo().setEnabled(false);
				gtr.disableAll();
			}
		});

		this.sptPnToDoMain.setLeftComponent(gtl.getPanel());
		this.sptPnToDoMain.setRightComponent(gtr.getPanel());
	}

	private void saveChangesByIndex(ToDoDTO changedToDo, int lastIndex) {
		this.toDoModel.updateToDo(lastIndex, gtr.getChangedToDo());
	}

	public void addToDo() {
		ToDoDTO toDoDTOforRightPanel = gtl.addToDo();
		this.updateValuesRight(toDoDTOforRightPanel);
	}

	private void placeComponents() {
		this.pnlToDoMain.add(sptPnToDoMain);
	}

	private void updateValuesRight(ToDoDTO dto) {
		this.gtr.updateValues(dto);
	}

	public JPanel getPanel() {
		return this.pnlToDoMain;
	}

	public void saveChanges() {
		if (this.gtl.getLstToDos().getSelectedIndex() >= 0) {
			int selected = gtl.getLstToDos().getSelectedIndex();
			this.toDoModel.updateToDo(selected, gtr.getChangedToDo());
			this.gtl.updateListByIndex(selected);
		}
	}

}
