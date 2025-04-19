import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ListUtils {

    public static void viewList(ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d: %s%n", i, list.get(i));
            }
        }
    }

    public static void addItem(ArrayList<String> list, Scanner in) {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to add");
        list.add(item);
    }

    public static void deleteItem(ArrayList<String> list, Scanner in) {
        viewList(list);
        int index = SafeInput.getRangedInt(in, "Enter index to delete", 0, list.size() - 1);
        list.remove(index);
    }

    public static void insertItem(ArrayList<String> list, Scanner in) {
        viewList(list);
        int index = SafeInput.getRangedInt(in, "Enter index to insert at", 0, list.size());
        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert");
        list.add(index, item);
    }

    public static void moveItem(ArrayList<String> list, Scanner in) {
        viewList(list);
        int from = SafeInput.getRangedInt(in, "Move FROM index", 0, list.size() - 1);
        int to = SafeInput.getRangedInt(in, "Move TO index", 0, list.size());
        String item = list.remove(from);
        list.add(to, item);
    }

    public static String openFile(ArrayList<String> list, Scanner in) throws IOException {
        String filename = SafeInput.getNonZeroLenString(in, "Enter filename to open") + ".txt";
        list.clear();
        list.addAll(Files.readAllLines(Paths.get(filename)));
        System.out.println("Loaded list from " + filename);
        return filename;
    }

    public static void saveFile(String filename, ArrayList<String> list) throws IOException {
        Files.write(Paths.get(filename), list);
        System.out.println("Saved list to " + filename);
    }

    public static void promptSaveBeforeContinue(ArrayList<String> list, Scanner in) throws IOException {
        boolean save = SafeInput.getYNConfirm(in, "You have unsaved changes. Save now?");
        if (save) {
            String filename = SafeInput.getNonZeroLenString(in, "Enter filename to save") + ".txt";
            saveFile(filename, list);
        }
    }
}
