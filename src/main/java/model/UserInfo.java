package model;
/**
 * This class implement user which contains username, full name, password.
 */
public class UserInfo {
    private String usersUserName;
    private String fullName;
    private String passWord;
    private String activeUser;

    /**
     * This method create a user with specified username, full name and password.
     * @param usersUserName user write a username for creating an account.
     * @param fullName user type full name for the account.
     * @param passWord user generating a password for the account.
     * @param activeUser will be attached automatically after creating username
     *                  which will be used to spotting the active user.
     */
    public UserInfo(String usersUserName, String fullName, String passWord, String activeUser) {
        this.usersUserName = usersUserName;
        this.fullName = fullName;
        this.passWord = passWord;
        this.activeUser = activeUser;
    }
    public UserInfo() {
    }
    public String getUsersUserName() {
        return usersUserName;
    }
    public void setUsersUserName(String usersUserName) {
        this.usersUserName = usersUserName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getActiveUser() {
        return activeUser;
    }
    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }
}
