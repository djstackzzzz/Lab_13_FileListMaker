import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value = 0;
        boolean done = false;
        do {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                if (value >= low && value <= high) {
                    done = true;
                } else {
                    System.out.println("Out of range.");
                }
            } else {
                System.out.println("Invalid input.");
                pipe.next();
            }
        } while (!done);
        pipe.nextLine(); // clear newline
        return value;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response;
        do {
            System.out.print(prompt + ": ");
            response = pipe.nextLine();
        } while (!response.matches(regEx));
        return response;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response;
        do {
            System.out.print(prompt + " [Y/N]: ");
            response = pipe.nextLine().trim().toUpperCase();
        } while (!response.equals("Y") && !response.equals("N"));
        return response.equals("Y");
    }
}
