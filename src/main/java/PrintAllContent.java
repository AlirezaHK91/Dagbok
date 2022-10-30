import com.fasterxml.jackson.databind.ObjectMapper;
import model.DiaryInfo;

import java.io.IOException;
import java.util.List;

/**
 * This class contains a print method.
 */
public class PrintAllContent {
    /**
     * This method is used for reading the active user's contents from json file and printing them out.
     */
    public static void printUsers() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ControlJsonAndLists.tempDiaryList = List.of(objectMapper.readValue(ControlJsonAndLists.diaryFile, DiaryInfo[].class));

        for (DiaryInfo item : ControlJsonAndLists.tempDiaryList) {
            if (item.getUserName().equals(ControlJsonAndLists.userInfo.getActiveUser())) {
                System.out.println("...............................\nDate: " + item.getDateOfText());
                System.out.println("Titel: " + item.getMyTitle());
                System.out.println("\nText: " + item.getMyText());
                System.out.println("...............................");
            }
        }
    }
}

