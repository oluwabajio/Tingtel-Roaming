package tingtel.android.models;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Balance {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String SimName;

    private String SimUuid;

    private String type;

    private String Message;

    private Date date;


    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSimName() {
        return SimName;
    }

    public void setSimName(String simName) {
        SimName = simName;
    }

    public String getSimUuid() {
        return SimUuid;
    }

    public void setSimUuid(String simUuid) {
        SimUuid = simUuid;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
