import com.fasterxml.jackson.databind.ObjectMapper;
import model.DiaryInfo;
import model.UserInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * 1- This class gets information from users to create account such as username, full name and password.
 * .....................................................................................................................
 * 2- This gets also information to make a diary like, title and text.
 */
public class GetInfoFromUser {
    public static Scanner scanner;

    /**
     * This method throws a unique exception when the user tries to create an account
     * with a username that already exist.
     * @throws UserException
     */
    public static void checkUserName() throws UserException {
        throw new UserException("""
                ...............................
                Du försöker skriva ett användarnamn som redan finns!
                ...............................""");
    }

    /**
     * 1- This method gets user's information like username, full name and password to create an account.
     * .................................................................................................................
     * 2- This method prevents user to choose a username that is already exist, then a warning comes up which is
     * " You try to write a username which already exist ".
     * .................................................................................................................
     * 3- This method prevents users to use special characters like (!,"'+*<>{#}¤@~`)%£$€(&:;/?½=¨^|´) except these
     * characters ( _ , - , . ) for creating username and full name.
     * .................................................................................................................
     * 4- If the user write any of the special characters while writing username and full name, those characters will
     * be deleted automatically afterwards.
     * .................................................................................................................
     * 5- User is not even allowed to use space between words while creating username. The space will be deleted
     * automatically.
     * .................................................................................................................
     * 6- User can not leave any of the fields empty. User must write something, otherwise it will be repeated.
     * .................................................................................................................
     * 7- When user is done with writing username, full name and password, the information will be added to the
     * user list and json file.
     */
    public static void getUserNameInfo() throws IOException {
        scanner= new Scanner(System.in);
        ObjectMapper objectMapper= new ObjectMapper();
        String specialChar= "[!,\"'+*<>{#}¤@~`)%£$€(&:;/?½=¨^|´]";

        String getUserName="";
        String fullName="";
        String pass="";

        while (getUserName.isEmpty()) {

            boolean userExistAlready = false;
            do {

                System.out.print("Välj ett användarnamn : ");
                getUserName = scanner.nextLine().replaceAll(" ", "").replaceAll(specialChar, "");

                try {
                    for (UserInfo item : ControlJsonAndLists.userInfoList) {
                        userExistAlready = getUserName.equalsIgnoreCase(item.getUsersUserName());
                        if (userExistAlready) {
                            checkUserName();
                        }
                    }

                } catch (UserException e) {
                    System.out.println(e.compareUserName);
                }
            } while (userExistAlready);
        }
        while (fullName.isEmpty()) {
            ControlJsonAndLists.userInfo.setUsersUserName(getUserName);
            System.out.print("Skriv ditt fullständiga namn : ");
            fullName = scanner.nextLine().replaceAll(specialChar, "");
            ControlJsonAndLists.userInfo.setFullName(fullName);
        }
        while (pass.isEmpty()) {
            System.out.print("Skriv ditt lösenord : ");
            pass = scanner.nextLine();
            ControlJsonAndLists.userInfo.setPassWord(pass);
        }
        System.out.print("...............................\n");

        String activeUser = ControlJsonAndLists.userInfo.getUsersUserName();

        ControlJsonAndLists.userInfoList.add(new UserInfo(getUserName, fullName, pass, activeUser));

        objectMapper.writeValue(ControlJsonAndLists.userFile, ControlJsonAndLists.userInfoList);

        System.out.println("Du har skapat ett konto.");
        System.out.print("...............................\n");
    }

    /**
     * 1- By this method, user create content by writing title, text. Date will be added automatically.
     * .................................................................................................................
     * 2- User can not leave any of the fields empty. User must write something, otherwise it will be repeated.
     * .................................................................................................................
     * 3- When user is done with writing the title and text, the content including date, will be added to
     * the diary list and json file.
     * .................................................................................................................
     * 4- Right after this process, the content will be printed out.
     */
    public static void getDiaryInfo(){
        scanner= new Scanner(System.in);
        ObjectMapper objectMapper= new ObjectMapper();
        String titel="";
        String text="";

        while (text.isEmpty()) {
            System.out.print("Skriv titel : ");
            text = scanner.nextLine();
            ControlJsonAndLists.diaryInfo.setMyTitle(titel);
        }
        while (titel.isEmpty()){
            System.out.print("Skriv din text : ");
            titel = scanner.nextLine();
            ControlJsonAndLists.diaryInfo.setMyText(text);
        }

        LocalDate localDate = LocalDate.now();
        String date = localDate.toString();
        ControlJsonAndLists.diaryInfo.setDateOfText(date);

        String userName = ControlJsonAndLists.diaryInfo.setUserName
                (ControlJsonAndLists.userInfo.getActiveUser());

        ControlJsonAndLists.diaryInfoList.add(new DiaryInfo(titel, text, date, userName));

        try {
            objectMapper.writeValue(ControlJsonAndLists.diaryFile, ControlJsonAndLists.diaryInfoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("...............................");
        System.out.println("* Nytt inlägg *\n");
        System.out.println("Date: " + date);
        System.out.println("Titel: " + titel);
        System.out.println("\nText: " + text);
        System.out.println("...............................");
    }
}
