package screens.par_search;

import database.ParDsGateway;
import database.ParFileUser;
import par_unfollow_org_use_case.ParUnfollowOrgInputBoundary;
import par_unfollow_org_use_case.ParUnfollowOrgInteractor;
import par_unfollow_org_use_case.ParUnfollowOrgOutputBoundary;
import par_unfollow_org_use_case.ParUnfollowOrgResponseModel;
import screens.par_follow_org_screens.ParFollowOrgController;
import screens.par_follow_org_screens.ParFollowOrgPresenter;
import screens.par_follow_org_screens.ParUnfollowOrgController;
import screens.par_follow_org_screens.ParUnfollowOrgPresenter;
import screens.par_home.ParHomePage;
import par_follow_org_use_case.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ParSearchOrgResultsPageActionListener implements ActionListener {


    public ParSearchOrgResultsPage parSearchOrgResultsPage;
    private String orgName;

    /**Constructor for the organizer search results page action listener.
     * It takes a search results page and an organizer name as inputs
     * and stores them as attributes.
     *
     * @param parSearchOrgResultsPage The participant's organizer search results page
     * @param orgName The name of the organizer the action is being performed on
     */
    public ParSearchOrgResultsPageActionListener(ParSearchOrgResultsPage parSearchOrgResultsPage, String orgName) {

        this.parSearchOrgResultsPage = parSearchOrgResultsPage;
        this.orgName = orgName;
    }

    /**A method to deal with actions on the organizer search results page.
     * If the action command is back, the participant's home page is displayed.
     * If the action command is follow or unfollow, the participant attempts to
     * follow or unfollow the organizer.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Back")) {
            this.parSearchOrgResultsPage.dispose();
            new ParHomePage(this.parSearchOrgResultsPage.getParUsername());
        } else if (actionCommand.equals("Follow " + this.orgName)) {

            ParDsGateway par = new ParFileUser();
            ParFollowOrgOutputBoundary presenter = new ParFollowOrgPresenter();
            ParFollowOrgInputBoundary interactor = new ParFollowOrgInteractor(par,presenter);
            ParFollowOrgController controller = new ParFollowOrgController(interactor);
            String parUserName = this.parSearchOrgResultsPage.getParUsername();
            this.parSearchOrgResultsPage.dispose();
            ParFollowOrgResponseModel responseModel;
            try {
                responseModel = controller.follow(parUserName,this.orgName);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(this.parSearchOrgResultsPage, responseModel.getMessage());
            new ParHomePage(this.parSearchOrgResultsPage.getParUsername());

        } else if (actionCommand.equals("Unfollow " + this.orgName)) {

            ParDsGateway par = new ParFileUser();
            ParUnfollowOrgOutputBoundary presenter = new ParUnfollowOrgPresenter();
            ParUnfollowOrgInputBoundary interactor = new ParUnfollowOrgInteractor(par, presenter);
            ParUnfollowOrgController controller = new ParUnfollowOrgController(interactor);
            String parUserName = this.parSearchOrgResultsPage.getParUsername();
            this.parSearchOrgResultsPage.dispose();
            ParUnfollowOrgResponseModel responseModel = null;
            try {
                responseModel = controller.unfollow(parUserName, this.orgName);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this.parSearchOrgResultsPage, responseModel.getMessage());
            new ParHomePage(this.parSearchOrgResultsPage.getParUsername());
        }

    }
}
