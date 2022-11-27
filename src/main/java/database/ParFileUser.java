package database;

import java.sql.*;
import java.util.ArrayList;

import static tutorial.HelloWorld.*;

public class ParFileUser implements ParDsGateway {

    /**This is a tool method used to store the username and password of the participant to the database.
     *
     * @param username The username of the participant
     * @param password The password of the participant
     */
    public void utilStorePar(String username, String password) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "insert into parfile(username, password) values('" + username + "','" + password + "');" ;
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0 ){
                System.out.println("Success");
            }
            else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }

    }

    /**This is a tool method used to delete the participant from the database.
     *
     * @param username THe username of the participant
     */
    public void utilDeletePar(String username) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "delete from parfile where username = '" + username + "';";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0 ){
                System.out.println("Success");
            }
            else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
    }

    /**This is a tool method used to add following relationship between participants and organizations to the database.
     *
     * @param par_username The username of the participant
     * @param org_username The username of the organization
     */
    public void utilAddParFollowing(String par_username, String org_username) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "insert into follow_org_par(par_username, org_username) values('" + par_username + "','" + org_username + "');" ;
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0 ){
                System.out.println("Success");
            }
            else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }


    }

    /**This is a tool method used to delete following relationship between participants and organizations from the database.
     * The par must be following the org, otherwise nothing happens.
     *
     * @param par_username The username of the participant
     * @param org_username The username of the organization
     */
    public void utilDeleteParFollowOrg(String par_username, String org_username) throws ClassNotFoundException, SQLException {
            Statement stmt = null;
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
                String sql = "delete from follow_org_par where par_username = '" + par_username + "' and org_username = '" + org_username + "';";
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sql);
                System.out.println(sql);
                if (count > 0) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failure");
                }

            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException();
            } catch (SQLException e) {
                throw new SQLException();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            }

        }

    /**This a tool method used to delete relationship between participants and past events from the database.
     *The participant must register the past event, otherwise nothing would happen.
     *
     * @param par_username The username of the participant
     * @param event_title The title of the event
     */
    public void utilDeleteParPastEvent(String par_username, String event_title) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "delete from past_events_for_par where par_username = '" + par_username + "' and event_title = '" + event_title + "';";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }



    }

    /**This a tool method used to add relationship between participants and upcoming events to the database.
     *
     * @param par_username The username of the participant
     * @param event_title The title of the event
     */
    public void utilAddParUpcomingEvent(String par_username, String event_title) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "insert into upcoming_events_for_par(par_username, event_title) values('" + par_username + "','" + event_title + "');" ;
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0 ){
                System.out.println("Success");
            }
            else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }


    }

    /**This a tool method used to delete relationship between participants and upcoming events from the database.
     * This par must register in the upcoming event, otherwise nothing happens.
     *
     * @param par_username The username of the participant
     * @param event_title The title of the event
     */
    public void utilDeleteParUpcomingEvent(String par_username, String event_title) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "delete from upcoming_events_for_par where par_username = '" + par_username + "' and event_title = '" + event_title + "';";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }

    }

    /**This is a tool method used to get all organizations followed by a participant.
     *
     * @param par_username The username of the participant
     * @return All organizations followed by the participant
     */
    public ArrayList<String> utilGetAllFollowing(String par_username) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<String> l = new ArrayList<>(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select org_username from follow_org_par where par_username = '" + par_username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
//            rs.next();
            while (rs.next()){
                l.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
        return l;



    }

    /**This is a tool method used to get all past events registered by a participant.
     *
     * @param par_username The username of the participant
     * @return All past events registered by the participant
     */
    public ArrayList<String> utilGetAllPastEvent(String par_username) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<String> l = new ArrayList<>(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select event_title from past_events_for_par where par_username = '" + par_username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()){
                l.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
        return l;

    }

    /**This is a tool method used to get all upcoming events registered by a participant.
     *
     * @param par_username The username of the participant
     * @return All upcoming events registered by the participant
     */
    public ArrayList<String> utilGetAllUpcomingEvent(String par_username) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<String> l = new ArrayList<>(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select event_title from upcoming_events_for_par where par_username = '" + par_username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()){
                l.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
        return l;

    }

    /**This is a tool method used to update the password of a participant.
     *
     * @param par_username The username of the participant
     * @param new_password The new password of the participant
     */
    public void utilPasswordUpdating(String par_username, String new_password) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "update parfile set password = '" + new_password + "' where username = '" + par_username + "';";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
    }

    /**This is a tool method used to add new notification to the participant.
     *
     * @param par_username The username of the participant
     * @param new_notification The new notification need to be sent to the participant
     */
    public void utilNotificationUpdating(String par_username, String new_notification) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "insert ignore into par_notification(par_username, notification) values('" + par_username + "','" + new_notification + "')";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
    }

    /**This is a tool method used to get all notifications of the participant.
     *
     * @param par_username The username of the participant
     * @return All notifications of the participant
     */
    public ArrayList<String> UtilGetNotifications(String par_username) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<String> l = new ArrayList<>(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select notification from par_notification where par_username = '" + par_username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
//            rs.next();
            while (rs.next()){
                l.add(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stmt != null){
                stmt.close();
            }
            if (conn != null){
                conn.close();
            }

        }
        return l;}

    /**This is a tool method used to delete all notifications of the participant.
     * It no notifications before, nothing change.
     *
     * @param par_username The username of the participant
     */
    public void UtilClearNotifications(String par_username) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "delete from par_notification where par_username = '" + par_username + "';";
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            System.out.println(sql);
            if (count > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
    }


    /**This is a tool method used to get the password of the participant.
     *
     * @param username The username of the participant
     * @return The password of the participant
     */
    public String utilGetPassword(String username) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select password from parfile where username = '" + username + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            password = rs.getString("password");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        }finally {
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return password;
    }

    /**This is a tool method returning whether the username exist.
     * If not found, returned false, which is the default value of the boolean stored in method.
     *
     * @param username The username that need to be used to check existence
     * @return Whether the username exists
     */
    public boolean utilCheckIfUsernameExist(String username) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean WhetherExist = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getDatabaseUrl(), getDatabaseUsername(), getDatabasePassword());
            String sql = "select exists(select * from parfile where username = '" + username + "');";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == 1){
                WhetherExist = true;
            }
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (SQLException e) {
            throw new SQLException();
        }finally {
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return WhetherExist;
    }





    /**This a method used to add relationship between participants and upcoming events to the database.
     * This method called a tool method called utilAddParUpcomingEvent.
     *
     * @param par_username The username of the participant
     * @param title The title of the event
     */
    public void registerEvent(String par_username, String title) throws SQLException, ClassNotFoundException {
        utilAddParUpcomingEvent(par_username,title);
    }

    /**This a method used to delete relationship between participants and upcoming events from the database.
     * This par must register in the upcoming event, otherwise nothing happens.
     * This method called a tool method called utilDeleteParUpcomingEvent.
     *
     * @param par_username The username of the participant
     * @param title The title of the event
     */
    public void leaveEvent(String par_username, String title) throws SQLException, ClassNotFoundException {
        utilDeleteParUpcomingEvent(par_username,title);
    }

    /**This is a method used to get the password of the participant.
     * This method called a tool method called utilGetPassword.
     *
     * @param username The username of the participant
     * @return The password of the participant
     */
    public String getPassword(String username) throws SQLException, ClassNotFoundException {
        return utilGetPassword(username);
    }

    /**This is a method used to update the password of a participant.
     * This method called a tool method called utilPasswordUpdating.
     *
     * @param username The username of the participant
     * @param new_password The new password of the participant
     */
    public void setPassword(String username, String new_password) throws SQLException, ClassNotFoundException {
        utilPasswordUpdating(username, new_password);
    }

    /**This is a method used to get all notifications of the participant.
     * It calls a tool method called UtilGetNotifications.
     *
     * @param par_username The username of the participant
     * @return All notifications of the participant
     */
    public ArrayList<String> getNotifications(String par_username) throws SQLException, ClassNotFoundException {
        return UtilGetNotifications(par_username);
    }

    /**This is a method used to get all upcoming events registered by a participant.
     * This method called a tool method called utilGetAllUpcomingEvent.
     *
     * @param username The username of the participant
     * @return All the upcoming events registered by the participant
     */
    public ArrayList<String> getUpcomingEvents(String username) throws SQLException, ClassNotFoundException {
        return utilGetAllUpcomingEvent(username);
    }

    /**This is a method used to get all past events registered by a participant.
     * This method called a tool method called utilGetAllPastEvent.
     *
     * @param username The username of the participant
     * @return All the past events registered by the participant
     */
    public ArrayList<String> getPastEvents(String username) throws SQLException, ClassNotFoundException {
        return utilGetAllPastEvent(username);
    }

    /**This is a method used to get all organizations followed by a participant.
     * This method called a tool method called utilGetAllFollowing.
     *
     * @param username The username of the participant
     * @return All the organizations followed by the participant
     */
    public ArrayList<String> getFollowedOrg(String username) throws SQLException, ClassNotFoundException {
        return utilGetAllFollowing(username);
    }

    /**This is a method used to add following relationship between participants and organizations to the database.
     * This method called a tool method called utilAddParFollowing.
     *
     * @param par_username The username of the participant
     * @param org_username The username of the organization
     */
    public void followOrg(String par_username, String org_username) throws SQLException, ClassNotFoundException {
        utilAddParFollowing(par_username,org_username);
    }

    /**This is a method used to add following relationship between participants and organizations to the database.
     * This method called a tool method called utilAddParFollowing.
     *
     * @param par_username The username of the participant
     * @param org_username The username of the organization
     */
    public void unfollowOrg(String par_username, String org_username) throws SQLException, ClassNotFoundException {
        utilDeleteParFollowOrg(par_username,org_username);
    }


    /**This is a method returning whether the username exist.
     * This method calls a tool method called utilCheckIfUsernameExist.
     * If not found, returned false, which is the default value of the boolean stored in method.
     *
     * @param username The username that need to be used to check existence
     * @return Whether the username exists
     */
    public boolean checkIfUsernameExist(String username) throws SQLException, ClassNotFoundException {
        return utilCheckIfUsernameExist(username);
    }

    /**This is a method used to store the username and password of the participant to the database.
     * This method called a tool method called utilStorePar.
     *
     * @param username The username of the participant
     * @param password The password of the participant
     */
    public void createPar(String username, String password) throws SQLException, ClassNotFoundException {
        utilStorePar(username, password);
    }

    /**This is a method used to delete the participant from the database.
     * The method tooled method including
     * utilGetAllPastEvent, utilGetAllUpcomingEvent, utilGetAllFollowing.
     * It will delete all relationship between the participant and events.
     * And it will remove all following relationships.
     *
     * @param username The username of the participant
     */
    public void deletePar(String username) throws SQLException, ClassNotFoundException {
        //First delete relationships with events
        //Then delete relationships with organizations
        //Then delete itself

        //First breaking relationship with past events
        ArrayList<String> All_past_events = utilGetAllPastEvent(username);
        for (String all_past_event : All_past_events) {
            utilDeleteParPastEvent(username, all_past_event);
        }
        ArrayList<String> All_upcoming_events = utilGetAllUpcomingEvent(username);
        for (String all_upcoming_event : All_upcoming_events) {
            utilDeleteParUpcomingEvent(username, all_upcoming_event);
        }

        //Then break relationship with followings
        ArrayList<String> All_following = utilGetAllFollowing(username);
        for (String s : All_following) {
            utilDeleteParFollowOrg(username, s);
        }

        UtilClearNotifications(username);

        utilDeletePar(username);

    }

    /**This is a method used to add notification to a participant.
     * This method called a tool method called utilNotificationUpdating.
     *
     * @param username The username of the participant
     * @param new_notification The new notification sent to the participant
     */
    public void addNotification(String username, String new_notification) throws SQLException, ClassNotFoundException {
        utilNotificationUpdating(username,new_notification);
    }

    /**This is a method used to delete all notifications of the participant. It no notifications before, nothing change.
     * This method called a tool method called UtilClearNotifications.
     *
     * @param username The username of the participant
     */
    @Override
    public void clearNotifications(String username) throws SQLException, ClassNotFoundException {
        UtilClearNotifications(username);
    }

}
