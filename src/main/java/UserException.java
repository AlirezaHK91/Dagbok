/**
 * This class is an extension of Exception class.
 */
public class UserException extends Exception{
    String compareUserName;
    public UserException(String compareUserName) {
        this.compareUserName = compareUserName;
    }
}
