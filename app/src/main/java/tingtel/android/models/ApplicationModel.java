package tingtel.android.models;

public class ApplicationModel {

    private String appstate;
    private String iccid;
    private String name;
    private String Simname;
    private String ServiceType;
    private boolean ussdReceiverCheck;

    public String getAppstate() {
        return appstate;
    }

    public void setAppstate(String appstate) {
        this.appstate = appstate;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimname() {
        return Simname;
    }

    public void setSimname(String simname) {
        Simname = simname;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }


}
