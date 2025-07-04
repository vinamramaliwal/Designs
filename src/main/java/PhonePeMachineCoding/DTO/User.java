package PhonePeMachineCoding.DTO;

public class User {
    private final int userId;
    private final String userName;
    private final String phoneNumber;
    private final String emailId;

    public User(int userId, String userName, String phoneNumber, String emailId) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
}
