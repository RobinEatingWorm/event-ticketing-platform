package par_unfollow_org_use_case;

public class UnfollowOrgRequestModel {

    private String par_username, org_username;

    public UnfollowOrgRequestModel(String par_username, String org_username){
        this.par_username=par_username;
        this.org_username=org_username;
    }

    public String getPar_username() {
        return par_username;
    }

    public void setPar_username(String par_username) {
        this.par_username = par_username;
    }

    public void setOrg_username(String org_username) {
        this.org_username = org_username;
    }

    public String getOrg_username() {
        return org_username;
    }
}
