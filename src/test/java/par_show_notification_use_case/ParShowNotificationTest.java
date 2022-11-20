package par_show_notification_use_case;
import database.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import screens.par_show_notification.ParShowNotificationController;
import screens.par_show_notification.ParShowNotificationPresenter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParShowNotificationTest {
    ParDsGateway parDsGateway = new ParFileUser();

    ParShowNotificationPresenter presenter = new ParShowNotificationPresenter();
    ParShowNotificationInputBoundary interactor = new ParShowNotificationInteractor(presenter, parDsGateway);
    ParShowNotificationController controller = new ParShowNotificationController(interactor);
    ParShowNotificationResponseModel responseModel = null;

    @Test
    @Order(1)
    void test_PrepareFailureView_No_Notification_Found(){
        try {
            responseModel = controller.showNotification("7654321");
            assert(false);
        } catch (Exception e) {
            assertEquals("You have no notification!", e.getMessage());
        }
    }

    @Test
    @Order(2)
    void test_PrepareSuccessView_Found_Notifications(){
        try {
            String notifications =
                    "Event TeamMeeting3 is about to happen at 11-30 0:0" + "\n" +
                            "Event TeamMeeting2 was over at 11-12 0:0.";
            System.out.println(notifications);
            responseModel = controller.showNotification("654321");
            assertEquals(notifications, responseModel.getNotifications());
        } catch (Exception e) {
            assert(false);
        }
    }
}
