import com.fasterxml.jackson.databind.ObjectMapper;
import model.DiaryInfo;
import model.UserInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 1- This class created to gather lists, directories and a methode which is used for reading information from
 * json files, in one place.
 * .....................................................................................................................
 * 2- This class also helps to easily access the lists and json files in another classes and methods in an
 * organized way.
 */
public class ControlJsonAndLists {
    /**
     * This is the directory of diaryInfo's json file.
     */
    public static File diaryFile = new File("src/main/resources/diary.json");
    /**
     * This an object of the DiaryInfo class.
     */
    public static DiaryInfo diaryInfo = new DiaryInfo();
    /**
     * This is the list where all the diary content are stored.
     */
    public static ArrayList<DiaryInfo> diaryInfoList = new ArrayList<>();
    /**
     * This is a temporary list which contains temporary information.
     */
    public static List<DiaryInfo> tempDiaryList = new ArrayList<>();
    /**
     * This is the directory of userInfo's json file.
     */
    public static File userFile = new File("src/main/resources/user.json");
    /**
     * This an object of the UserInfo class.
     */
    public static UserInfo userInfo = new UserInfo();
    /**
     * This is the list where all the user information are stored.
     */
    public static List<UserInfo> userInfoList = new ArrayList<>();
    /**
     * This is a temporary list which contains temporary information .
     */
    public static List<UserInfo> tempUserList = new ArrayList<>();

    /**
     * This method is used for reading json files by temporary lists and then add all the information to
     * the main lists.
     */
    public static void readOldJson(){
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            ControlJsonAndLists.tempUserList = List.of(objectMapper.readValue(ControlJsonAndLists.userFile,
                    UserInfo[].class));
            ControlJsonAndLists.userInfoList.addAll(ControlJsonAndLists.tempUserList);
        } catch (Exception e) {

        }

        try {
            ControlJsonAndLists.tempDiaryList = List.of(objectMapper.readValue(ControlJsonAndLists.diaryFile,
                    DiaryInfo[].class));
            ControlJsonAndLists.diaryInfoList.addAll(ControlJsonAndLists.tempDiaryList);
        } catch (Exception e) {

        }
    }
}
