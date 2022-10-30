import java.io.IOException;
import java.util.Scanner;

/**
 * This class contains 3 methods which presenting the menus.
 */
public class Menus {
    /**
     * This method (menu1) showing the main menu to the user.
     */
    public static void menu1() {
        System.out.println("...............................");
        String[] userMenu = {" Skapa konto", " Logga in på ditt konto", " Ändra lösenord", " Radera konto",
                " Avsluta program"};
        int rowNumber = 1;
        for (String item : userMenu) {
            System.out.println(+rowNumber + ". " + item);
            rowNumber++;
        }
        System.out.print("...............................\n");

    }

    /**
     * This method (menu2) includes the second menu.
     */
    public static void menu2() {
        System.out.println("...............................");
        String[] createContentMenu = {" Läs din dagbok", " Skriv dagbok", " Gå till huvudmenyn"};
        int rowNumber1 = 1;
        for (String item1 : createContentMenu) {
            System.out.println(rowNumber1 + ". " + item1);
            rowNumber1++;
        }
        System.out.print("...............................\n");
    }

    /**
     * This method only shows the second menu when the user's username and password verification is succeeded.
     */
    public static void openMenu2AfterVerification() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            Menus.menu2();

            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> PrintAllContent.printUsersContent();
                case "2" -> GetInfoFromUser.getDiaryInfo();
                case "3" -> System.out.println("""
                        ...............................
                        Du stängde din dagbok.
                        ...............................""");
                default -> System.out.println("""
                        ...............................
                        Var snäll och välj en siffra mellan 1 och 3
                        ...............................""");
            }
        } while (!choice.equals("3"));
    }
}
