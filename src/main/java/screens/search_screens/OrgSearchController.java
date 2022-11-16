package screens.search_screens;

import org_search_use_case.OrgSearchInputBoundary;
import org_search_use_case.OrgSearchRequestModel;
import org_search_use_case.OrgSearchResponseModel;

import java.util.ArrayList;

public class OrgSearchController {

    final OrgSearchInputBoundary userInput;

    public OrgSearchController(OrgSearchInputBoundary userInput) {
        this.userInput = userInput;
    }

    public OrgSearchResponseModel orgSearch(String query,String parUserName) {
        OrgSearchRequestModel requestModel = new OrgSearchRequestModel(query,parUserName);
        return userInput.orgSearch(requestModel);
    }
}