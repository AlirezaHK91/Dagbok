import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserInfo;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class UserService {
    /**
     * This class is created to control and verify user's information and give users possibility
     * to make changes in their account.
     */
    public static Scanner scanner;

    /**
     * This method throws an exception when the program checks if the username is found or not.
     *
     * @throws UserException
     */
    public static void checkUserNameFound() throws UserException {
        throw new UserException("");
    }

    /**
     * 1- This method is created to authenticate users by asking them to confirm both username and password.
     * User can not leave any of the fields empty. User must type something, otherwise user will be asked again.
     * .................................................................................................................
     * 2- User can type username in lowercase but the password must be exactly the same as when it's
     * chosen (for safety's sake).
     * .................................................................................................................
     * 3- If username found, goes to the next step, otherwise user will be asked again to write another username,
     * until it matches with an existing username.
     * .................................................................................................................
     * 4- Next step is to verify the password. This method checks if the password is correct and belongs to the
     * username which is authenticated earlier. If the password is not matched, user get a warning which is
     * " Wrong password! try again! ".
     * .................................................................................................................
     * 5- After username and password are verified successfully, the user will be welcomed and the second menu will
     * be open.
     */
    public static void verifyUser() {
        scanner = new Scanner(System.in);

        String userInput = "";
        String userPass = "";

        int counter = 1;
        System.out.println("...............................\n* Lista över konton *\n");
        for (UserInfo item : ControlJsonAndLists.userInfoList) {
            System.out.println("#" + counter + " " + item.getUsersUserName());
            counter++;
        }

        try {

            while (userInput.isEmpty()) {

                boolean userExistAlready = false;

                do {
                    System.out.print("...............................\nVälj och skriv ditt användarnamn: ");
                    userInput = scanner.nextLine();

                    try {
                        for (UserInfo item : ControlJsonAndLists.userInfoList) {
                            userExistAlready = !userInput.equalsIgnoreCase(item.getUsersUserName());
                            if (!userExistAlready) {
                                checkUserNameFound();
                            }
                        }
                    } catch (UserException e) {
                        e.getStackTrace();
                    }
                } while (userExistAlready);
            }
            while (userPass.isEmpty()) {
                System.out.print("Skriv ditt lösenord: ");
                userPass = scanner.nextLine();
            }
            //*\\
            ControlJsonAndLists.userInfo.setActiveUser(userInput);

            for (UserInfo item : ControlJsonAndLists.userInfoList) {
                if (userInput.equalsIgnoreCase(item.getUsersUserName())) {
                    if (userPass.equals(item.getPassWord())) {

                        System.out.println("...............................\n* Nu är du inloggad *\n");
                        System.out.println("Aktiv användare: " + item.getFullName()
                                + " (" + item.getUsersUserName() + ")" + "\n");

                        Menus.openMenu2AfterVerification();

                    } else {
                        System.out.println("""
                                ...............................
                                Fel lösenord! försök igen
                                ...............................""");
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 1- This method helps users to remove their own accounts.
     * .................................................................................................................
     * 2- Firstly, user will be asked to confirm both username and password. User can not leave any of the fields empty.
     * User must type something, otherwise user will be asked again to write another username, until it matches
     * with an existing username.
     * .................................................................................................................
     * 3- User can type username in lowercase but the password must be exactly the same as when it's chosen
     * (for safety's sake).
     * .................................................................................................................
     * 4- Next step is to verify the password. This method checks if the password is correct and belongs to the
     * username which is authenticated earlier. If the password is not matched, user get a warning
     * which is " Wrong password! try again! ", otherwise it goes to the next step.
     * .................................................................................................................
     * 5- After username and password are verified successfully, user will be asked to either confirm the removing
     * account's process or cancel the process by choosing Y and N .
     * .................................................................................................................
     * 6- If user choose " Y " the process will be completed by showing a confirmation and the account will be deleted
     * from both user list and json file.
     * .................................................................................................................
     * 7- If user choose " N " the process will be stopped right away and no action will be taken.
     */

    public static void removeUser() throws IOException {
        Scanner scanner1 = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();

        int counter = 1;
        System.out.println("...............................\n* Lista över konton *\n");
        for (UserInfo item : ControlJsonAndLists.userInfoList) {
            System.out.println("#" + counter + " " + item.getUsersUserName());
            counter++;
        }
        String userInput = "";
        String userInputPass = "";
        boolean userExistAlready = false;

        do {

            System.out.print("...............................\nVälj och skriv ditt användarnamn : ");
            userInput = scanner1.nextLine();

            try {
                for (UserInfo item : ControlJsonAndLists.userInfoList) {
                    userExistAlready = !userInput.equalsIgnoreCase(item.getUsersUserName());
                    if (!userExistAlready) {
                        UserService.checkUserNameFound();
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        } while (userExistAlready);

        while (userInputPass.isEmpty()) {
            System.out.print("Bekräfta ditt lösenord : ");
            userInputPass = scanner1.nextLine();
        }

        try {
            for (UserInfo item : ControlJsonAndLists.userInfoList) {
                int index = (ControlJsonAndLists.userInfoList.indexOf(item));

                if (item.getUsersUserName().equalsIgnoreCase(userInput)) {
                    if (item.getPassWord().equals(userInputPass)) {
                        String dubbleCheck = "";
                        while (dubbleCheck.isEmpty()) {

                            System.out.print("Är du säker?.....Y/N?");
                            scanner = new Scanner(System.in);
                            dubbleCheck = scanner.nextLine().toLowerCase();
                        }

                        switch (dubbleCheck) {
                            case "y":
                                ControlJsonAndLists.userInfoList.remove(index);
                                System.out.println("""
                                        ...............................
                                        Nu är ditt konto raderat.
                                        ...............................""");
                                break;
                            case "n":
                                System.out.println("""
                                        ...............................
                                        Du valde att INTE radera ditt konto.
                                        ...............................""");
                                break;
                            default:
                                System.out.println("...............................\n '" +
                                        dubbleCheck + "' Känns inte igen som ett kommando! Försök igen." +
                                        "\n................................");
                        }
                    } else {
                        System.out.println("""
                                ...............................
                                Fel lösenord! försök igen
                                ...............................""");
                    }
                }
            }
        } catch (ConcurrentModificationException e) {

        }
        objectMapper.writeValue(ControlJsonAndLists.userFile, ControlJsonAndLists.userInfoList);
    }

    /**
     * 1- This method gives the users possibility to change their password.
     * .................................................................................................................
     * 2- user will be asked to confirm both username and password. User can not leave any of the fields empty.
     * User must type something, otherwise user will be asked again to write another username, until it matches
     * with an existing username.
     * .................................................................................................................
     * 3- User can type username in lowercase but the password must be exactly the same as when it's chosen
     * (for safety's sake).
     * .................................................................................................................
     * 4- After username verified, user will be asked to write the existing password to confirm that the account belongs
     * to the user who is trying to change password. If the password is not matched, user gets a warning which is
     * " Wrong password! try again! ", otherwise it goes to the next step which is choosing a new password.
     * .................................................................................................................
     * 5- By choosing a new password, the old password will be replaced by the new one and the user can no longer log in
     * to the account with the old password.
     * .................................................................................................................
     * 6- Afterwards a message will be shown which is  " Your password is changed ".
     */
    public static void changePassword() {
        ObjectMapper objectMapper = new ObjectMapper();
        scanner = new Scanner(System.in);

        String userInput = "";
        String userPass = "";

        int counter = 1;
        System.out.println("...............................\n* Lista över konton *\n");
        for (UserInfo item : ControlJsonAndLists.userInfoList) {
            System.out.println("#" + counter + " " + item.getUsersUserName());
            counter++;
        }

        try {

            while (userInput.isEmpty()) {

                boolean userExistAlready = false;

                do {
                    System.out.print("...............................\nVälj och skriv ditt användarnamn: ");
                    userInput = scanner.nextLine();

                    try {
                        for (UserInfo item : ControlJsonAndLists.userInfoList) {
                            userExistAlready = !userInput.equalsIgnoreCase(item.getUsersUserName());
                            if (!userExistAlready) {
                                checkUserNameFound();
                            }
                        }
                    } catch (UserException e) {
                        e.getStackTrace();
                    }
                } while (userExistAlready);
            }
            while (userPass.isEmpty()) {
                System.out.print("Skriv ditt nuvarande lösenord : ");
                userPass = scanner.nextLine();
            }
            for (UserInfo item : ControlJsonAndLists.userInfoList) {
                int index = (ControlJsonAndLists.userInfoList.indexOf(item));
                if (userInput.equalsIgnoreCase(item.getUsersUserName())) {
                    if (userPass.equals(item.getPassWord())) {
                        String newUserPass = "";
                        while (newUserPass.isEmpty()) {
                            System.out.print("Skriv ditt nya lösenord : ");
                            newUserPass = scanner.nextLine();
                        }
                        ControlJsonAndLists.userInfoList.add(new UserInfo(item.getUsersUserName(),
                                item.getFullName(), newUserPass, item.getActiveUser()));

                        ControlJsonAndLists.userInfoList.remove(index);

                        objectMapper.writeValue(ControlJsonAndLists.userFile, ControlJsonAndLists.userInfoList);

                        System.out.println("""
                                ...............................
                                Ditt lösenord är ändrat.
                                ...............................""");

                    } else {
                        System.out.println("""
                                ...............................
                                Fel lösenord! försök igen
                                ...............................""");
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
