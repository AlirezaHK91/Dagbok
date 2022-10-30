package model;

/**
 * This class implements a diary which contains title, text and date.
 */
public class DiaryInfo {
    private String myTitle;
    private String myText;
    private String dateOfText;
    private String userName;

    /**
     * 1- This method create a content with specified title, text, date.
     * .................................................................................................................
     * 2- "userName" is used for clarifying the user who creates the content.
     */

    public DiaryInfo(String myTitle, String myText, String dateOfText, String userName) {
        this.myTitle = myTitle;
        this.myText = myText;
        this.dateOfText = dateOfText;
        this.userName = userName;
    }
    public DiaryInfo() {
    }
    public String getMyTitle() {
        return myTitle;
    }
    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }
    public String getMyText() {
        return myText;
    }
    public void setMyText(String myText) {
        this.myText = myText;
    }
    public String getDateOfText() {
        return dateOfText;
    }
    public void setDateOfText(String dateOfText) {
        this.dateOfText = dateOfText;
    }
    public String getUserName() {
        return userName;
    }
    public String setUserName(String userName) {
        this.userName = userName;
        return userName;
    }
}
