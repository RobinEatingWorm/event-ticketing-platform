package par_show_notification_use_case;

public class ParShowNotificationRequestModel {

    private String username;
    public ParShowNotificationRequestModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}