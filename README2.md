# Project Description

The program allows event organizations to post their scheduled events and let participants join the events.
There are two different kinds of users, which are participants and organizations. They use separate databases and can choose to register or login separately by selecting radio buttons.
The participants can use the search bar through all available events and join the event, they can also delete the joined event. The participants can also choose to follow the organizers to be alerted by new coming events. After joining, they’ll get the notification before the event which sent by the organizations.
By signing in organizations’ accounts, they can see all events scheduled (published, unpublished, or past events), and number of followers. Organizations can also create events by filling in event’s title, place, description and time. The created event will be saved in the unpublished event page and they’ll need to “Publish” the event to make the event can be searched by the participants. They can also notify the participants who joined the event by clicking the “notify” button.
In participants’ accounts, they can see all events they have registered (past or future) and the organizations that they followed.

*Special Case to consider: We are using MySQL and DataGrip for data storage. Details will be explianed in the Database section.*
## Starter File
`src/main/java/tutorial/HellowWord`

The starter file launches the Login Page.

There are 3 static variables in the class:

- databaseUrl = "jdbc:mysql://localhost:3306/db2"
  - This is used for finding the database located in DataGrip.
- databaseUsername = "root"
  - This is the default username of your database, usually you will not need to change it.
- databasePassword = "vvks1309"
  - This is the password of your database, you need to set it when you are registering your account for your database.

The get methods of the above 3 static variables are used by `src/main/java/database`, these variables will be held fixed for all operations.

## Entities

We do not have the entities structure as we are using MySQL to store our data.
But from the concept perspective, we have *event*, *organization*, and *participant* to be responsible for the Enterprise Business Rules.

Only for demonstration purposes, each of those has the following attributes:

- Event
  - String organization: the username of the organization who launched the event.
  - String title: the title of the event.
  - *editable* String description: the description of the event.
  - *editable* String location: the location of the event.
  - *editable* ArrayList<Integer\> time: the time of the event, consists of year, month, day, hour, minute.
- Organization (User)
  - String username: the username of the organization.
  - *editable* String password: the password of the organization.
  - *editable* ArrayList<String\> unpublishedEvents: the titles of events in the "draft box" of the organization. The organization can create new events. The organization can choose to change any information of existing events except for their title. The organization can also choose to delete the events here.
  - *editable* ArrayList<String\> upcomingEvents: the titles of events that are published by the organization from the "draft box". At this point, the organization cannot change any information about the events, but can choose to delete them.
  - ArrayList<String\> pastEvents: the titles of events which are published and whose time are in the past (comparing with system time by LocalDateTime).
  - ArrayList<String\> followers: the usernames of the organization's followers (all are participants).
- Participant  (User)
  - String username: the username of the participant.
  - *editable* String password: the password of the participant.
  - *editable* ArrayList<String\> upcomingEvents: the titles of events that are published by the organization from the "draft box". At this point, the organization cannot change any information about the events, but can choose to delete them. 
  - ArrayList<String\> pastEvents: the titles of events which the participant joined and whose time are in the past (comparing with system time by LocalDateTime)
  - *editable* ArrayList<String\> followedOrganizations: the usernames of the participant's followed Organizations. The participant can choose to unfollow any of them.


*"*editable*" means the value of attribute can be changed by the user through means provided on UI.*


## Use Cases

All folders ending with use_case are the use cases of the project.
Each consists of 5 types of class/interface: InputBoundary, Interactor, OutputBoundary, RequestModel, ResponseModel (except for extract_information_use_case).

We have 17 use cases in total.
If the naming starts with user_, then the user type does not play a significant part for its Application Business Rules.
If the naming starts with par_, then the use case is specifically responsible for a functionality belongs to participant.
If the naming starts with org_, then the use case is specifically responsible for a functionality belongs to organization.

There are a few special use cases that are used in more than one place in the project, usually across the user types:
- `src/main/java/extract_information_use_case`: This is designed to assist views to show information stored in the database for Clean Architecture purposes. It is not tied to any specific type of user or screen.
- `src/main/java/upcoming_to_past_use_case`: This is an auto-triggered use case that do not need the user to explicitly demand for it. It will convert the user's all relevant upcoming events whose time is in the past (comparing with system time by LocalDateTime) to past events.
- `src/main/java/notify_event_use_case`: This is used to notify the user about changes or updates of relevant events. It can be called in various places in the project as the function is so commonly used.

## Database
`src/main/java/database`

*Important Setting to change: go to build.gradle, add dependency for MySQL: implementation 'mysql:mysql-connector-java:8.0.30'*

The database folder consists of 3 DsGateways and 3 FileUsers for event, organization, and participant. The FileUsers are directly connected to DataGrip. The guide for installing DataGrip and using the test database is at the end of this file.

Explain ALL tables in DataGrip, what does each of them do.

- eventfile:
   - Store the information of every event.
   - Contains row: title, description, location, year, month, day, hour, minute.
   - title have a primary key meaning it cannot be repeated.
- orgfile:
   - Store the information of every organization.
   - Contains row: username, password.
   - username is a primary key, so it cannot have repeat terms.
- parfile:
   - Store the information of every participant.
   - Contains row: username, password.
   - username is a primary key, so it cannot have repeat terms.
- unpublished_events_for_org:
   - When an organization create an event and make it a draft, the event should be stored at the eventfile but the relationship between the organization and the event are stored here.
   - Store the relationship of unpublished events and organizations.
   - This table is specifically used for unpublished events, published or past events should not show here.
   - Have two rows: org_username, event_title.
   - Org_username and event_title form a primary key together, means one pair of relationship can not show twice.
   - Foreign key of 'org_username' is linked to 'username' at orgfile and foreign key of 'event_title' is linked to 'title' at eventfile.
- past_events_for_org:
   - When an organization created an event, and the event have finished, the relationship between the event and the organization are stored here.
   - Store the relationship of past events and organizations.
   - This table is specifically used for past events, unpublished or upcoming events should not be here.
   - Have two rows: org_username, event_title.
   - Org_username and event_title form a primary key together, means one pair of relationship can not show twice.
   - Foreign key of 'org_username' is linked to 'username' at orgfile and foreign key of 'event_title' is linked to 'title' at eventfile.
- upcoming_events_for_org:
   - When an organization create an event, the event is going to happen in the future, its relationship with organization are stored here.
   - Store the relationship of upcoming events and organizations.
   - This table is specifically used for upcoming events, unpublished or past events should not be here.
   - Have two rows: org_username, event_title.
   - Org_username and event_title form a primary key together, means one pair of relationship can not show twice.
   - Foreign key of 'org_username' is linked to 'username' at orgfile and foreign key of 'event_title' is linked to 'title' at eventfile.
- past_events_for_par:
   - When a participant registered an event, and the event finished, the relationship betwene the event and the participant are stored here.
   - Store the relationship of past events and participants.
   - This table is specifically used for past events, upcoming events should not appear.
   - Have two rows: par_username, event_title.
   - Par_username and event_title form a primary key together, means one pair of relationship should not appear twice.
   - Foreign key of 'par_username' is linked to 'username' at parfile and foreign key of 'event_title' is linked to 'title' at eventfile.
- upcoming_events_for_par:
   - When a participant register an event, and the event will happen in the future, the relationship of them are stored here.
   - Store the relationship of upcoming events and participants.
   - This table is specifically used for upcoming events, past events should not appear.
   - Have two rows: par_username, event_title.
   - Par_username and event_title form a primary key together, means one pair of relationship should not appear twice.
   - Foreign key of 'par_username' is linked to 'username' at parfile and foreign key of 'event_title' is linked to 'title' at eventfile.
- follow_org_par:
   - If a participant followed an organization, the relationship are stored in this table.
   - Store the relationship of organizations and participants.
   - Have two rows: par_username, org_username.
   - Foreign key of 'par_username' is linked to 'username' at parfile and foreign key of 'org_username' is linked to 'username' at orgfile.
   - Par_username and org_username form a primary key together, meaning the following relationship pair cannot exist twice.
- par_notification:
   - When someone sent participant an notification, the relationship between the notification and the participant are stored.
   - Store the relationship of notification and participants.
   - Have two rows: par_username, notification.
   - Foreign key of 'par_username' is linked to 'username' at parfile.
   - Notification is a String, one username can be paired with multiple notifications.