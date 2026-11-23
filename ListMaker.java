import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab 11 ListMaker - a simple line editor that keeps a list of Strings in an ArrayList.
 * Menu: A - Add, D - Delete, I - Insert, P - Print, Q - Quit.
 * All input goes through the SafeInput library.
 *
 * @author Darrell Lawson
 */
public class ListMaker
{
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean done = false;
        String cmd;

        do
        {
            displayList();
            displayMenu();
            cmd = SafeInput.getRegExString(in, "Enter a command", "[AaDdIiPpQq]");
            cmd = cmd.toUpperCase();

            switch (cmd)
            {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    done = quit();
                    break;
            }
        } while (!done);

        System.out.println("Goodbye!");
    }

    /** Adds a new item to the end of the list. */
    private static void addItem()
    {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item);
        System.out.println("Added \"" + item + "\" to the end of the list.");
    }

    /** Deletes the item the user picks by its number in the display. */
    private static void deleteItem()
    {
        if (list.size() == 0)
        {
            System.out.println("The list is empty, there is nothing to delete.");
            return;
        }

        int num = SafeInput.getRangedInt(in, "Enter the number of the item to delete", 1, list.size());
        String removed = list.remove(num - 1);
        System.out.println("Deleted \"" + removed + "\" from the list.");
    }

    /** Inserts a new item at the location number the user picks. */
    private static void insertItem()
    {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
        // list.size() + 1 lets the user insert at the very end too
        int loc = SafeInput.getRangedInt(in, "Enter the location number for the new item", 1, list.size() + 1);
        list.add(loc - 1, item);
        System.out.println("Inserted \"" + item + "\" at location " + loc + ".");
    }

    /** Prints the list. */
    private static void printList()
    {
        System.out.println();
        System.out.println("Printing the list:");
        displayList();
    }

    /** Asks the user if they are sure and returns true if they want to quit. */
    private static boolean quit()
    {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit");
    }

    /** Shows the numbered list so the user can pick items by number. */
    private static void displayList()
    {
        System.out.println();
        System.out.println("=============== Current List ===============");
        if (list.size() == 0)
        {
            System.out.println("  [ the list is empty ]");
        }
        else
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("%3d. %s%n", i + 1, list.get(i));
            }
        }
        System.out.println("============================================");
    }

    /** Shows the menu of commands. */
    private static void displayMenu()
    {
        System.out.println("A - Add   D - Delete   I - Insert   P - Print   Q - Quit");
    }
}
