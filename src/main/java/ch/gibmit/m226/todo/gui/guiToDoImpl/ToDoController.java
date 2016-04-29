package ch.gibmit.m226.todo.gui.guiToDoImpl;

import ch.gibmit.m226.todo.bl.ToDo;
import ch.gibmit.m226.todo.dto.ToDoDTO;
import ch.gibmit.m226.todo.gui.interfaces.IToDoController;

/**
 * Created by hecol on 25/04/16.
 */
public class ToDoController implements IToDoController {

    private ToDo toDo = new ToDo();
    private ToDoModel toDoModel;

    public ToDoController(ToDoModel toDoModel) {
        this.toDoModel = toDoModel;
    }

    @Override
    public void addToDo(ToDoDTO toDoDTO) {
        toDo.add(toDoDTO);
        getAllToDos();
    }

    @Override
    public void getAllToDos() {
    	toDoModel.refreshToDoList(toDo.getToDos());
    }
}