package tingtel.android.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NetworksCode {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="code")
    private String code;
    @ColumnInfo(name="desc")
    private String desc;
    @ColumnInfo(name="tag")
    private String tag;

    public NetworksCode(String name, String title, String code, String desc, String tag) {

        this.name = name;
        this.title = title;
        this.code = code;
        this.desc = desc;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static NetworksCode[] populateNetworksCodes() {

        return new NetworksCode[] {

                new NetworksCode("airtel", "Airtime Balance", "*123#", "This service enables you to check your current airtime balance which is available for calls", "tag"),
                new NetworksCode("airtel", "Data Balance", "*141*11*0#", "This service allows you to check your current data balance(in MB) which is available for internet service", "tag"),
                new NetworksCode("airtel", "Check Phone Number", "*121*3*4#", "This service enables you to know your Airtel phone number", "tag"),
                new NetworksCode("airtel", "Borrow Airtime", "*500*amt#", "This service allows you to borrow airtime from Airtel./namt: the amount you want to borrow", "tag"),
                new NetworksCode("airtel", "Please call back", "*140*num#", "This service enables you to place a call back message to any of your contacts", "tag"),
                new NetworksCode("airtel", "Transfer Airtime", "*901#", "This Airtel service enables you to make airtime transfer to another number. you have to enter the amount to be transfer and the recipient number in the fields provided.After clicking send, this will automatically send an SMS to 432 which will trigger the transaction", "tag"),
                new NetworksCode("airtel", "Data Subscription", "*141#", "This ussd code allow you to subscribe to purchase any Airtel data bundle", "buy_data"),
                new NetworksCode("airtel", "Load Airtime", "*126*pin#", "This ussd code allow you to load airtime", "load_airtime"),



                new NetworksCode("9mobile", "Airtime Balance", "*232#", "This service code enables you to check your current airtime balance available for calls towards all network operators", "tag"),
                new NetworksCode("9mobile", "Data Balance", "*228#", "This service allows you to check your current data balance(in MB) available for internet service", "tag"),
                new NetworksCode("9mobile", "Check Phone Number", "*248#", "This service allows you to check your Phone Number", "tag"),
                new NetworksCode("9mobile", "Borrow Airtime", "*665#", "With this service, you can borrow airtime from 9mobile and pay back later./n amt: the amount you want to borrow", "tag"),
                new NetworksCode("9mobile", "Join etisalat you and me", "*244*2#", "Etisalat You and Me is a reward scheme which allows easy starter customers enjoy free credit every week to call an Etisalat You and Me special number anytime.If you opt-in for Etisalat U and Me, you will get N100 free credit during the week you recharge N100 and if you recharge up to N200, you will get N300 free credit. You can enjoy up to N1200 free credit every month by recharging with a minimum of N200 per week", "tag"),
                new NetworksCode("9mobile", "Data Subscription", "*200*3#", "This ussd code allow you to subscribe to purchase any 9mobile data bundle", "buy_data"),
                new NetworksCode("9mobile", "Load Airtime", "*222*pin#", "This ussd code allow you to load airtime", "load_airtime"),




                new NetworksCode("glo", "Airtime Balance", "*124*1#", "This service code will give you your account balance", "tag"),
                new NetworksCode("glo", "Data Balance", "*127*0#", "This service code helps you to check your data balance(in MB) which is available for internet service", "tag"),
                new NetworksCode("glo", "Check my phone number", "*135*8#", "Are you always having difficulties remembering your Glo phone number? then this code will help you!", "tag"),
                new NetworksCode("glo", "Borrow Airtime", "*321#", "This service enables you to borrow airtime for calls towards all networks from Glo and pay back later", "tag"),

                new NetworksCode("glo", "Buy data plans", "*777#", "Dialing this code will give you the list of all Glo data plans available on the network which you can then chose from", "buy_data"),
                new NetworksCode("glo", "Check credit", "#124#", "This service will enable you to get your current airtime balance available for calls towards all network operators", "tag"),
                new NetworksCode("glo", "Please call back", "*125*num#", "This Glo service enables you send a call back to any Glo subscriber. The most special thing about 9jaCodes is that you don't have to remember the recipient number, 9jaCodes will pick this contact from your contacts list for you", "tag"),
                new NetworksCode("glo", "Share Data", "*127*01*num#", "With this service, you can share your data bundle with any other subscriber. just click the button and fill the required fields, amazingly 9jaCodes will pick the recipient phone number from your contacts list for you", "tag"),
                new NetworksCode("glo", "Load Airtime", "*123*pin#", "This Ussd Code allows you to load airtime", "load_airtime"),



                new NetworksCode("mtn", "Check Airtime", "*556#", "This service will enable you to get your current airtime balance available for calls towards MTN subscribers and all other network operators", "ussd"),
                new NetworksCode("mtn", "Check Airtime 2", "*123*1*3#", "This ussd code allows you check your MTN airtime balance for calls", "ussd"),
                new NetworksCode("mtn", "Check Data balance", "*131*4#", "This service allows you check your data balance on mtn network. This can be used for internet services", "tag"),
                new NetworksCode("mtn", "Check Data balance 2", "*559#", "This service code helps you to check your data balance(in MB) which is available for internet service", "tag"),
                new NetworksCode("mtn", "Borrow Airtime", "*606#", "This service code allows your borrow either airtime or data to be paid later", "ussd"),
                new NetworksCode("mtn", "Buy Data", "*131#", "This service code allows you to subscribe to any mtn data plan of your choice", "buy_data"),
                new NetworksCode("mtn", "Recharge Airtime", "*555*PIN#", "This service code allows you recharge your mtn sim", "recharge"),
                new NetworksCode("mtn", "Please Call Me Back", "*133*1*num#", "This service code allows you request other users to call you back", "ussd"),
                new NetworksCode("mtn", "Please Send Me Credit", "*133*2*num#", "This service code allows you request other users to send you airtime", "ussd"),
                new NetworksCode("mtn", "Cancel Data Auto-Renewal", "*123*5#", "This service code allows you unsubscribe from all mtn data plans auto renewal", "ussd"),
                new NetworksCode("mtn", "Change My Tariff Plan", "*123*1*2#", "This service code allows you unsubscribe from all mtn data plans auto renewal", "ussd"),
                new NetworksCode("mtn", "Change My Tariff Plan", "*123*1*2#", "This service code allows you to change your mtn tariff plan", "ussd"),
                new NetworksCode("mtn", "Change transfer pin", "*600*oldpin*newpin*newpin#", "This service helps you to change your airtime transfer Personal Identification Number (PIN). After entering the code, you enter the old PIN and enter the new PIN twice", "tag"),
                new NetworksCode("mtn", "Check account balance (summary)", "*141#", "This service will give you the summary of your account balance", "tag"),
                new NetworksCode("mtn", "Check detail account balance", "*141*1#", "This will give you your account balance and every details associated with the balance", "tag"),
                new NetworksCode("mtn", "To know my phone number", "*123#", "This code will help you to know your MTN phone number if at any time you can't remember it", "tag"),
                new NetworksCode("mtn", "Please call back", "*133*num#", "This service enables you to send a call back to any contact. Amazingly 9jaCodes will help you to pick the recipient number from your contacts list if you can't remember it", "tag"),
                new NetworksCode("mtn", "Please send me credit", "*133*2*num#", "With this MTN service, you can ask someone to credit your phone for you", "tag"),
                new NetworksCode("mtn", "Sms bonuses", "*159*3#", "This service gives you access to all SMS bonuses available on the network", "tag"),
                new NetworksCode("mtn", "Transfer airtime", "*600*num*amt*pin#", "This service helps you to transfer MTN airtime to another subscriber. The amazing thing about 9jaCodes is that you don't have to remember the recipient number before making this transaction, you can just pick this number from your contacts list", "tag"),
                new NetworksCode("mtn", "Load Airtime", "*555*pin#", "This ussd code allows you to load airtime", "load_airtime"),


        };
    }
}
