import java.io.IOException;
import java.util.Scanner;

/**
 * This class include main method.
 */
public class Main {
    public static Scanner scanner;

    /**
     * Main method is where the program starts.
     */
    public static void main(String[] args) throws IOException {

        System.out.println("\nVälkommen till din dagbok \t\t¨Version 1.0¨\n");
        ControlJsonAndLists.readOldJson();
        scanner= new Scanner(System.in);

        String choice = "";
        while (choice.isEmpty()) {
            do {
                Menus.menu1();

                choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> GetInfoFromUser.getUserInfo();
                    case "2" -> UserService.verifyUser();
                    case "3" -> UserService.changePassword();
                    case "4" -> UserService.deleteAccount();
                    case "5" -> System.out.println("""
                            Nu stängs programmet.
                                                            
                            Tack att du valde vår app. Välkommen åter!""");
                    default -> System.out.println("""
                            ...............................
                            Ett fel uppstod! Välj en siffra mellan 1 till 3
                            ...............................""");
                }
            } while (!choice.equals("5"));
        }
    }
}

