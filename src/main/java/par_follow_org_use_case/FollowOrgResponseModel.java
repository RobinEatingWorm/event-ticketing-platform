package par_follow_org_use_case;

public class FollowOrgResponseModel {

    private String orgName;

    private String message;

    public FollowOrgResponseModel(String orgName){
        this.orgName=orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage(){return this.message;}
}
