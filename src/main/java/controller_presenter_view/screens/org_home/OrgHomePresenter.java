package controller_presenter_view.screens.org_home;

import use_cases.user_login_use_case.OrgHomeOutputBoundary;
import use_cases.user_login_use_case.UserLoginResponseModel;

public class OrgHomePresenter implements OrgHomeOutputBoundary {
    public UserLoginResponseModel prepareHomePageView(UserLoginResponseModel organization){
        return organization;
    }
}
