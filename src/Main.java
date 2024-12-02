import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> myArrList = new ArrayList<>();
        boolean done = false;
        boolean needsToBeSaved = false;
        String currentFileName = "";

        do {
            printListWithNumbers(myArrList);
            String input = SafeInput.getRegExString(in,
                    "\nA – Add\nD – Delete\nI – Insert\nV – View\nM – Move\nO – Open\nS – Save\nC – Clear\nQ – Quit\n",
                    "^[AaDdIiVvMmOoSsCcQq]$", "AaDdIiVvMmOoSsCcQq");

            switch (input.toUpperCase()) {
                case "A":
                    add(in, myArrList);
                    needsToBeSaved = true;
                    break;
                case "D":
                    delete(in, myArrList);
                    needsToBeSaved = true;
                    break;
                case "I":
                    insert(in, myArrList);
                    needsToBeSaved = true;
                    break;
                case "V":
                    printListWithNumbers(myArrList);
                    break;
                case "M":
                    move(in, myArrList);
                    needsToBeSaved = true;
                    break;
                case "O":
                    if (needsToBeSaved && !promptSave(in, myArrList, currentFileName)) {
                        System.out.println("Load canceled. Save your changes first.");
                        break;
                    }
                    currentFileName = open(in, myArrList);
                    needsToBeSaved = false;
                    break;
                case "S":
                    currentFileName = save(in, myArrList, currentFileName);
                    needsToBeSaved = false;
                    break;
                case "C":
                    clear(myArrList);
                    needsToBeSaved = true;
                    break;
                case "Q":
                    if (needsToBeSaved && !promptSave(in, myArrList, currentFileName)) {
                        System.out.println("Quit canceled. Save your changes first.");
                        break;
                    }
                    done = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (!done);
    }

    public static void printListWithNumbers(ArrayList<String> list) {
        System.out.println("Current list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public static void add(Scanner pipe, ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString(pipe, "Enter item to add");
        list.add(item);
        System.out.println("Item added.\n");
    }

    public static void delete(Scanner pipe, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty, there is nothing to delete.");
            return;
        }
        int itemNumber = SafeInput.getRangedInt(pipe, "Enter the number of the item to delete", 1, list.size());
        list.remove(itemNumber - 1);
        System.out.println("Item deleted.\n");
    }

    public static void insert(Scanner pipe, ArrayList<String> list) {
        int position = SafeInput.getRangedInt(pipe, "Enter the position to insert", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(pipe, "Enter the item to insert");
        list.add(position - 1, item);
        System.out.println("Item inserted!\n");
    }

    public static void move(Scanner pipe, ArrayList<String> list) {
        if (list.size() < 2) {
            System.out.println("Not enough items to move.");
            return;
        }
        int from = SafeInput.getRangedInt(pipe, "Enter the position to move from", 1, list.size());
        int to = SafeInput.getRangedInt(pipe, "Enter the position to move to", 1, list.size());
        String item = list.remove(from - 1);
        list.add(to - 1, item);
        System.out.println("Item moved.\n");
    }

    public static String open(Scanner pipe, ArrayList<String> list) {
        String fileName = SafeInput.getNonZeroLenString(pipe, "Enter the filename to open");
        try {
            list.clear();
            list.addAll(Files.readAllLines(Paths.get(fileName + ".txt")));
            System.out.println("File loaded successfully.");
            return fileName;
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            return "";
        }
    }

    public static String save(Scanner pipe, ArrayList<String> list, String currentFileName) {
        if (currentFileName.isEmpty()) {
            currentFileName = SafeInput.getNonZeroLenString(pipe, "Enter the filename to save");
        }
        try {
            Files.write(Paths.get(currentFileName + ".txt"), list);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
        return currentFileName;
    }

    public static void clear(ArrayList<String> list) {
        list.clear();
        System.out.println("List cleared.\n");
    }

    public static boolean promptSave(Scanner pipe, ArrayList<String> list, String currentFileName) {
        if (SafeInput.getYNConfirm(pipe, "You have unsaved changes. Save now?")) {
            save(pipe, list, currentFileName);
            return true;
        }
        return false;
    }
}