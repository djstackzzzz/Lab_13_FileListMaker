import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class FileListMaker {
    static boolean needsToBeSaved = false;
    static String currentFileName = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        boolean done = false;

        while (!done) {
            System.out.println("\nMenu: [A]dd [D]elete [I]nsert [M]ove [O]pen [S]ave [C]lear [V]iew [Q]uit");
            String choice = SafeInput.getRegExString(in, "Enter choice", "[AaDdIiMmOoSsCcVvQq]").toUpperCase();

            try {
                switch (choice) {
                    case "A":
                        ListUtils.addItem(list, in);
                        needsToBeSaved = true;
                        break;
                    case "D":
                        ListUtils.deleteItem(list, in);
                        needsToBeSaved = true;
                        break;
                    case "I":
                        ListUtils.insertItem(list, in);
                        needsToBeSaved = true;
                        break;
                    case "M":
                        ListUtils.moveItem(list, in);
                        needsToBeSaved = true;
                        break;
                    case "O":
                        if (needsToBeSaved) ListUtils.promptSaveBeforeContinue(list, in);
                        currentFileName = ListUtils.openFile(list, in);
                        needsToBeSaved = false;
                        break;
                    case "S":
                        if (currentFileName == null) currentFileName = SafeInput.getNonZeroLenString(in, "Enter filename") + ".txt";
                        ListUtils.saveFile(currentFileName, list);
                        needsToBeSaved = false;
                        break;
                    case "C":
                        list.clear();
                        needsToBeSaved = true;
                        System.out.println("List cleared.");
                        break;
                    case "V":
                        ListUtils.viewList(list);
                        break;
                    case "Q":
                        if (needsToBeSaved) ListUtils.promptSaveBeforeContinue(list, in);
                        done = true;
                        break;
                }
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }
}
