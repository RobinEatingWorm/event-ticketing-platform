package screens.par_search;

import par_search_event_use_case.ParSearchEventInputBoundary;
import par_search_event_use_case.ParSearchEventRequestModel;
import par_search_event_use_case.ParSearchEventResponseModel;

import java.sql.SQLException;

public class ParSearchEventController {


    final ParSearchEventInputBoundary userInput;

    /**The controller for the participant searching for events use case.
     * It takes a user input and returns the controller.
     *
     * @param userInput The user input from the participant searching for events.
     */
    public ParSearchEventController(ParSearchEventInputBoundary userInput) {
        this.userInput = userInput;
    }

    /**The main method that allows the participant to search for events.
     * It passes the request to search for events to the input boundary using the request model
     * data structure.
     *
     * @param query The search query
     * @param parUserName The username of the participant
     * @return Returns the response model, which is a data structure containing the search results
     * @throws SQLException This exception handles mistakes in SQL
     * @throws ClassNotFoundException This exception handles missing classes
     */
    public ParSearchEventResponseModel eventSearch(String query, String parUserName) throws SQLException, ClassNotFoundException {
        ParSearchEventRequestModel requestModel = new ParSearchEventRequestModel(query,parUserName);
        return userInput.eventSearch(requestModel);
    }


}
