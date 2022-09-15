package duke.tasklist;

import duke.listobjects.ListObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents TaskList which stores a list of tasks the user wants to store and implements the Serializable interface
 */
public class TaskList implements Serializable {

    private ArrayList<ListObject> tasksList;
    private static int numberOfTodos;
    private static int numberOfDeadlines;
    private static int numberOfEvents;

    /**
     * Constructs a TaskList with an empty ArrayList of ListObjects
     */
    public TaskList() {
        this.tasksList = new ArrayList<ListObject>();
    }

    /**
     * Sets the taskslist of the TaskList object to be input list
     * @param list ArrayList of ListObjects the user wished to store
     */

    public void setTasks(ArrayList<ListObject> list) {
        assert list!= null;
        this.tasksList = list;
    }

    /**
     * Returns the number of tasks stored
     * @return int representing number of tasks stored
     */
    public int getListLength() {
        return this.tasksList.size();
    }

    /**
     * Handles instructions on modifying a specific item in the list
     * @param instruction String representing type of action to be performed
     * @param itemNum int representing the item number to be modified
     */
    public String handleItem(String instruction, int itemNum) {

        assert itemNum >= 0;

        if (instruction.equals("UNMARK") || instruction.equals("MARK")) {
            ListObject currItem = tasksList.get(itemNum);
            assert currItem != null;
            currItem.switchStatus();
            return currItem.toString();
        } else if (instruction.equals("DELETE")) {
            ListObject currItem = tasksList.get(itemNum);
            assert currItem != null;
            tasksList.remove(itemNum);
            return currItem.toString();
        } else {
            return "Oh dear! I have forgotten what it is you asked...";
        }
    }

    /**
     * Adds a ListObject (task) to the taskslist field
     * @param obj ListObject representing the task to be added
     */

    public void handleItemAddition(ListObject obj) {

        this.tasksList.add(obj);
    }

    /**
     *Prints the list of tasks stored
     */
    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }


    /**
     * Returns the number of tasks stored
     * @return String representing the number of tasks stored
     */
    public String knowTaskCount(){
        return tasksList.size() + " tasks ";
    }

    /**
     * Returns the taskslist stored in the object
     * @return ArrayList representing the list of tasks stored
     */
    public ArrayList<ListObject> storeAllTasks() {
        return this.tasksList;
    }

    public String findByKeyword(String target) {
        Stream<ListObject> filteredOptions = tasksList.stream().filter(x -> x.hasWord(target));
        assert filteredOptions != null;
        List<ListObject> eligibleTasks = filteredOptions.collect(Collectors.toList());
        ArrayList<ListObject> filteredTasks = new ArrayList<>(eligibleTasks);
        TaskList tasksToDisplay = new TaskList();
        tasksToDisplay.setTasks(filteredTasks);
        return tasksToDisplay.toString();
    }

    /**
     * Returns the String representation of a TaskList object
     * @return String representation of TaskList object
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            str = str + i + ". " + tasksList.get(i).toString() + "\n";
        }
        return str;
    }

}