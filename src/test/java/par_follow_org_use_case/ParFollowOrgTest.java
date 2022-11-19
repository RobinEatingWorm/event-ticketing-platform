package par_follow_org_use_case;

import database.OrgDsGateway;
import database.OrgFileUser;
import database.ParDsGateway;
import database.ParFileUser;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org_delete_event_use_case.OrgDeleteEventResponseModel;
import screens.par_follow_org_screens.ParFollowOrgController;
import screens.par_follow_org_screens.ParFollowOrgPresenter;
import screens.par_upcoming_event.ParLeaveEventController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParFollowOrgTest {
    ParDsGateway par = new ParFileUser();
    ParFollowOrgOutputBoundary presenter = new ParFollowOrgPresenter();
    ParFollowOrgInputBoundary interactor = new ParFollowOrgInteractor(par,presenter);
    ParFollowOrgController controller = new ParFollowOrgController(interactor);
    ParFollowOrgResponseModel responseModel;

    /**Need to create "allyson" as a participant in parfile and "UBC" as an organization in orgfile
     */
    @Test
    @Order(1)
    void testParFollowOrg() {
        String parUsername = "allyson";
        String orgUsername = "UBC";
        try {
            responseModel = controller.follow(parUsername,orgUsername);
            assertEquals("You have followed UBC!", responseModel.getMessage());

            OrgDsGateway orgDsGateway = new OrgFileUser();
            ArrayList<String> followers = orgDsGateway.getFollowers(orgUsername);
            if (followers.contains(parUsername)) {assert(true);}
            else {assert(false);}
        } catch (Exception e) {
            assert(false);
        }
    }

}
