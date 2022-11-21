package screens.org_unpublished_event;

import org_publish_event_use_case.OrgPublishEventInputBoundary;
import org_publish_event_use_case.OrgPublishEventRequestModel;
import org_publish_event_use_case.OrgPublishEventResponseModel;

import java.sql.SQLException;

public class OrgPublishEventController {
    final OrgPublishEventInputBoundary userInput;
    //
    public OrgPublishEventController(OrgPublishEventInputBoundary accountGateway) {
        //Set the input interactor as instance
        this.userInput = accountGateway;
    }

    public OrgPublishEventResponseModel publish(String eventName) throws SQLException, ClassNotFoundException {
        //Generate a new request model by the given information
        OrgPublishEventRequestModel requestModel = new OrgPublishEventRequestModel(eventName);
        return userInput.publish(requestModel);
    }

}
