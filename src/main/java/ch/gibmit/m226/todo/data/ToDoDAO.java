package ch.gibmit.m226.todo.data;

import ch.gibmit.m226.todo.dto.ToDoDTO;

import java.util.ArrayList;

/**
 * Created by hecol on 22.04.2016.
 */
public interface ToDoDAO {
    void addToDo(ToDoDTO toDo);

    ArrayList<ToDoDAO> getAllToDo();
}
