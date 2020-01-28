package tingtel.android.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NetworksCode {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name="network")
    private String network;
    @ColumnInfo(name="code")
    private String code;
    @ColumnInfo(name="desc")
    private String desc;
    @ColumnInfo(name="tag")
    private String tag;

    public NetworksCode(String country, String network, String code, String desc, String tag) {
        this.country = country;
        this.network = network;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
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

                new NetworksCode("United Arab Emirates", "Du", "*135#", "", "tag"),
                new NetworksCode("United Arab Emirates", "Etisalat", "*121#", "", "tag"),
                new NetworksCode("United Arab Emirates", "Five Mobile", "*121#", "", "tag"),


                new NetworksCode("Afghanistan", "", "", "", "tag"),
                new NetworksCode("Antigua and Barbuda", "", "", "", "tag"),
                new NetworksCode("Anguilla", "", "", "", "tag"),
                new NetworksCode("Albania", "", "", "", "tag"),
                new NetworksCode("Armenia", "", "", "", "tag"),

                new NetworksCode("Angola", "Unitel", "*111#", "", "tag"),
                new NetworksCode("Angola", "Movicell", "*196#", "airtime", "tag"),
                new NetworksCode("Angola", "Movicell", "*200#", "data", "tag"),


                new NetworksCode("Antarctica", "", "", "", "tag"),


                new NetworksCode("Argentina", "Claro", "*611#", "", "tag"),
                new NetworksCode("Argentina", "Movistar", "To check balance, send any SMS to 444.", "", "tag"),
                new NetworksCode("Argentina", "Personal", "*150#", "", "tag"),


                new NetworksCode("AmericanSamoa", "", "", "", "tag"),


                new NetworksCode("Austria", "A1", "*101#", "", "tag"),
                new NetworksCode("Austria", "Magenta T", "*101#", "", "tag"),
                new NetworksCode("Austria", "Magenta T", "*114#", "", "tag"),
                new NetworksCode("Austria", "3 (= \"Drei\")", "Text \"GUT\" to 313 21.", "", "tag"),

                new NetworksCode("Australia", "Telstra", "#100#", "", "tag"),
                new NetworksCode("Australia", "Optus", "Text the number 1(one) to 9999.", "", "tag"),
                new NetworksCode("Australia", "Vodafone Australia", "Call 1512 from your Vodafone phone or text 'BAL' to 1511", "", "tag"),

                new NetworksCode("Aruba", "", "", "", "tag"),
                new NetworksCode("Åland Islands", "", "", "", "tag"),
                new NetworksCode("Bosnia and Herzegovina", "", "", "", "tag"),
                new NetworksCode("Barbados", "", "", "", "tag"),
                new NetworksCode("Bangladesh", "", "", "", "tag"),
                new NetworksCode("Belgium", "", "", "", "tag"),


                new NetworksCode("Burkina Faso", "Orange", "*160#", "", "tag"),
                new NetworksCode("Burkina Faso", "Telmob", "*101#", "airtime", "tag"),
                new NetworksCode("Burkina Faso", "Telmob", "*146#", "data", "tag"),
                new NetworksCode("Burkina Faso", "Telecel", "*122#", "airtime", "tag"),
                new NetworksCode("Burkina Faso", "Telecel", "*144#", "data", "tag"),


                new NetworksCode("Bulgaria", "", "", "", "tag"),


                new NetworksCode("Bahrain", "Batelco", "*122#", "", "tag"),
                new NetworksCode("Bahrain", "Zain", "*142#", "", "tag"),
                new NetworksCode("Bahrain", "STC", "*124#", "", "tag"),


                new NetworksCode("Burundi", "Econet", "*405#", "airtime", "tag"),
                new NetworksCode("Burundi", "Econet", "*405*0#", "data", "tag"),
                new NetworksCode("Burundi", "Lumitel", "*131#", "", "tag"),
                new NetworksCode("Burundi", "Smart Mobile", "TEXT BAL TO 2200", "", "tag"),


                new NetworksCode("Saint Barthélemy", "", "", "", "tag"),



                new NetworksCode("Benin", "Mtn", "*124#", "airtime", "tag"),
                new NetworksCode("Benin", "Mtn", "*130*2#", "data", "tag"),
                new NetworksCode("Benin", "Moov", "*100#", "airtime", "tag"),
                new NetworksCode("Benin", "Moov", "*300*6#", "data", "tag"),



                new NetworksCode("Bermuda", "", "", "", "tag"),
                new NetworksCode("Brunei Darussalam", "", "", "", "tag"),
                new NetworksCode("Bolivia, Plurinational State of", "", "", "", "tag"),
                new NetworksCode("Bonaire", "", "", "", "tag"),


                new NetworksCode("Brazil", "Vivo", "Check balance by texting ‘SALDO’ to 8000", "", "tag"),
                new NetworksCode("Brazil", "TIM", "*222#", "", "tag"),
                new NetworksCode("Brazil", "Claro", "*555#", "", "tag"),
                new NetworksCode("Brazil", "Oi", "You can check balance by typing *805", "", "tag"),


                new NetworksCode("Bahamas", "", "", "", "tag"),
                new NetworksCode("Bhutan", "", "", "", "tag"),
                new NetworksCode("Bouvet Island", "", "", "", "tag"),


                new NetworksCode("Botswana", "Mascom", "*167#", "airtime", "tag"),
                new NetworksCode("Botswana", "Mascom", "*123*4#", "data", "tag"),
                new NetworksCode("Botswana", "Orange", "*121#", "", "tag"),


                new NetworksCode("Belarus", "", "", "", "tag"),
                new NetworksCode("Belize", "", "", "", "tag"),


                new NetworksCode("Canada", "Rogers Wireless", "Call 1-888-764-3771 or *611 from your wireless device", "", "tag"),
                new NetworksCode("Canada", "Bell Mobility", "Call #321 free from your mobile phone", "", "tag"),
                new NetworksCode("Canada", "Telus Mobility", " Call #123 from your TELUS Prepaid device or 1-877-277-7745 from another phone", "", "tag"),
                new NetworksCode("Canada", "Freedom Mobile (formerly Wind)", "Call *123# and follow the instructions.", "", "tag"),


                new NetworksCode("Cocos (Keeling) Islands", "", "", "", "tag"),


                new NetworksCode("Congo, The Democratic Republic of the", "Mtn", "*141#", "", "tag"),
                new NetworksCode("Congo, The Democratic Republic of the", "Airtel", "*143#", "airtime", "tag"),
                new NetworksCode("Congo, The Democratic Republic of the", "Airtel", "*121#", "data", "tag"),



                new NetworksCode("Central African Republic", "Moov", "*140#", "", "tag"),
                new NetworksCode("Central African Republic", "Orange", "*123#", "airtime", "tag"),
                new NetworksCode("Central African Republic", "Orange", "#111*250#", "data", "tag"),



                new NetworksCode("Congo", "", "", "", "tag"),
                new NetworksCode("Switzerland", "", "", "", "tag"),


                new NetworksCode("Ivory Coast", "Mtn", "#100#", "airtime", "tag"),
                new NetworksCode("Ivory Coast", "Mtn", "*105*5*2#", "data", "tag"),
                new NetworksCode("Ivory Coast", "Orange", "#122#", "airtime", "tag"),
                new NetworksCode("Ivory Coast", "Orange", "#149*32#", "data", "tag"),
                new NetworksCode("Ivory Coast", "Moov", "*100#", "airtime", "tag"),
                new NetworksCode("Ivory Coast", "Moov", "*303*3*2*1#", "data", "tag"),

                new NetworksCode("Cook Islands", "", "", "", "tag"),


                new NetworksCode("Chile", "Entel", "To check balance dial *103# (free twice per day, $100 for each more)", "", "tag"),
                new NetworksCode("Chile", "Movistar", "Check balance by *303# for a $70 fee", "", "tag"),
                new NetworksCode("Chile", "Claro", "Text \"BALANCE\" to 725", "", "tag"),
                new NetworksCode("Chile", "WOM", "Visit the WOM website at wom.cl", "", "tag"),
                new NetworksCode("Chile", "Virgin Mobile", "To check your balance, call 103 for free, type *151# for $ 30", "", "tag"),


                new NetworksCode("Cameroon", "Mtn", "*155#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Mtn", "*157*99#", "data", "tag"),
                new NetworksCode("Cameroon", "Orange", "#123#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Orange", "*145*1#", "data", "tag"),
                new NetworksCode("Cameroon", "Nexttel", "*801#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Nexttel", "*865*1#", "data", "tag"),
                new NetworksCode("Cameroon", "Camtel", "*865*1#", "", "tag"),


                new NetworksCode("China", "China Mobile", "#130#", "", "tag"),
                new NetworksCode("China", "China Unicorn", "text \"101\" to 10010 for airtime balance or send text \"2082\" to 10010 for data balance.", "", "tag"),
                new NetworksCode("China", "China Telecom", " text \"101\" to \"10001\" on China Telecom to check balance OR call 10001 and follow the automated voice instructions to check your balance", "", "tag"),


                new NetworksCode("Colombia", "Claro", "Text \"ACTIVAR\" to 852 63 or Enter *103# followed by the send button", "", "tag"),
                new NetworksCode("Colombia", "Claro", "Enter *103# followed by the send button", "", "tag"),
                new NetworksCode("Colombia", "Movistar", "Text \"SALDO\" to 611 or Enter *611# followed by the send button", "", "tag"),
                new NetworksCode("Colombia", "Tigo Une", "*10#", "", "tag"),
                new NetworksCode("Colombia", "Avantel", "You can check Balance by typing  *901", "", "tag"),
                new NetworksCode("Colombia", "ETB", "Visit the Etb website at etb.co", "", "tag"),


                new NetworksCode("Costa Rica", "", "", "", "tag"),
                new NetworksCode("Cuba", "", "", "", "tag"),


                new NetworksCode("Cape Verde", "Cv Movel", "*123#", "", "tag"),
                new NetworksCode("Cape Verde", "Unitel", "*707#", "", "tag"),


                new NetworksCode("Curacao", "", "", "", "tag"),
                new NetworksCode("Christmas Island", "", "", "", "tag"),


                new NetworksCode("Cyprus", "Cyta Vodafone", "*110#", "", "tag"),
                new NetworksCode("Cyprus", "epic (formerly MTN)", "*202#", "", "tag"),
                new NetworksCode("Cyprus", "PrimeTel", "*133#", "", "tag"),


                new NetworksCode("Czech Republic", "", "", "", "tag"),


                new NetworksCode("Germany", "Deutsche Telekom", "*100#", "", "tag"),
                new NetworksCode("Germany", "Vodafone", "*100#", "", "tag"),
                new NetworksCode("Germany", "O2", "*101#", "", "tag"),


                new NetworksCode("Djibouti", "Evatis", "*168#", "airtime", "tag"),
                new NetworksCode("Djibouti", "Evatis", "*165#", "data", "tag"),


                new NetworksCode("Denmark", "Telia", "*120#", "", "tag"),
                new NetworksCode("Denmark", "Comviq", "*111#", "", "tag"),
                new NetworksCode("Denmark", "Lycamobile", "*137#", "", "tag"),


                new NetworksCode("Dominica", "", "", "", "tag"),


                new NetworksCode("Dominican Republic", "Claro", "*122#", "", "tag"),
                new NetworksCode("Dominican Republic", "Altice Dominicana", "#131#", "", "tag"),



                new NetworksCode("Algeria", "Djezzy", "Call 710 and follow the instructions", "", "tag"),
                new NetworksCode("Algeria", "Mobilis", "*600#", "", "tag"),
                new NetworksCode("Algeria", "Ooredoo", "*113# ", "", "tag"),


                new NetworksCode("Ecuado", "", "", "", "tag"),
                new NetworksCode("Estonia", "", "", "", "tag"),


                new NetworksCode("Egypt", "Vodafone", "*100#", "", "tag"),
                new NetworksCode("Egypt", "Orange", "*100#", "", "tag"),
                new NetworksCode("Egypt", "Etisalat", "*130#", "", "tag"),


                new NetworksCode("Western Sahara", "", "", "", "tag"),
                new NetworksCode("Eritrea", "", "", "", "tag"),

                new NetworksCode("Spain", "Movistar", "*123#", "", "tag"),
                new NetworksCode("Spain", "Vodafone", "*134#", "", "tag"),
                new NetworksCode("Spain", "Orange", "*111#", "", "tag"),
                new NetworksCode("Spain", "Yoigo", "*111#", "", "tag"),


                new NetworksCode("Ethiopia", "Ethio-Telecom", "*804#", "airtime", "tag"),
                new NetworksCode("Ethiopia", "Ethio-Telecom", "*999#", "data", "tag"),

                new NetworksCode("Finland", "", "", "", "tag"),
                new NetworksCode("Fiji", "", "", "", "tag"),
                new NetworksCode("Falkland Islands", "", "", "", "tag"),
                new NetworksCode("Micronesia, Federated States of", "", "", "", "tag"),
                new NetworksCode("Faroe Islands", "", "", "", "tag"),
                new NetworksCode("France", "", "", "", "tag"),


                new NetworksCode("Gabon", "Airtel", "*137#", "airtime", "tag"),
                new NetworksCode("Gabon", "Airtel", "*111*1#", "data", "tag"),
                new NetworksCode("Gabon", "Gabon Telecom", "#111#", "airtime", "tag"),
                new NetworksCode("Gabon", "Gabon Telecom", "*222#", "data", "tag"),


                new NetworksCode("United Kingdom", "Vodafone", "*#1345#", "", "tag"),
                new NetworksCode("United Kingdom", "Giffgaff", "*#100#", "", "tag"),
                new NetworksCode("United Kingdom", "Giffgaff", "DIAL 43430", "", "tag"),
                new NetworksCode("United Kingdom", "O2", "*#10#", "", "tag"),
                new NetworksCode("United Kingdom", "O2", "DIAL 4444", "", "tag"),
                new NetworksCode("United Kingdom", "EE", "TEXT BAL TO 150", "", "tag"),
                new NetworksCode("United Kingdom", "Three", "DIAL 1745", "", "tag"),
                new NetworksCode("United Kingdom", "Lebara", "*#1345#", "", "tag"),
                new NetworksCode("United Kingdom", "Lycamobile", "*131#", "", "tag"),
                new NetworksCode("United Kingdom", "T mobile", "DIAL 150", "", "tag"),
                new NetworksCode("United Kingdom", "Tesco Mobile", "DIAL 282", "", "tag"),



                new NetworksCode("Grenada", "", "", "", "tag"),
                new NetworksCode("Georgia", "", "", "", "tag"),
                new NetworksCode("French Guiana", "", "", "", "tag"),
                new NetworksCode("Guernsey", "", "", "", "tag"),


                new NetworksCode("Ghana", "Mtn", "*124#", "airtime", "tag"),
                new NetworksCode("Ghana", "Mtn", "*138*1*8#", "data", "tag"),
                new NetworksCode("Ghana", "Vodafone", "*124#", "airtime", "tag"),
                new NetworksCode("Ghana", "Vodafone", "*700*2#", "data", "tag"),
                new NetworksCode("Ghana", "AirtelTigo", "*133#", "airtime", "tag"),
                new NetworksCode("Ghana", "AirtelTigo", "*123#", "data", "tag"),
                new NetworksCode("Ghana", "Glo", "*124#", "airtime", "tag"),
                new NetworksCode("Ghana", "Glo", "*127#", "data", "tag"),


                new NetworksCode("Gibraltar", "", "", "", "tag"),
                new NetworksCode("Greenland", "", "", "", "tag"),


                new NetworksCode("Gambia", "Africell", "*132#", "airtime", "tag"),
                new NetworksCode("Gambia", "Africell", "*133#", "data", "tag"),
                new NetworksCode("Gambia", "Gambia Telecoms", "*906#", "airtime", "tag"),
                new NetworksCode("Gambia", "Gambia Telecoms", "*302#", "data", "tag"),
                new NetworksCode("Gambia", "Comium", "#101#", "", "tag"),
                new NetworksCode("Gambia", "Qcell", "*101#", "airtime", "tag"),
                new NetworksCode("Gambia", "Qcell", "*303*5#", "data", "tag"),


                new NetworksCode("Guinea", "", "", "", "tag"),
                new NetworksCode("Guadeloupe", "", "", "", "tag"),


                new NetworksCode("Equatorial Guinea", "Orange", "*124#", "airtime", "tag"),
                new NetworksCode("Equatorial Guinea", "Orange", "*145#", "data", "tag"),
                new NetworksCode("Equatorial Guinea", "Mtn", "*223#", "", "tag"),
                new NetworksCode("Equatorial Guinea", "Cellcom", "Call 8003099911", "airtime", "tag"),
                new NetworksCode("Equatorial Guinea", "Cellcom", "send bal to  28882", "data", "tag"),


                new NetworksCode("Greece", "Cosmote", "Send SMS with ΥΡ to 1314,", "", "tag"),
                new NetworksCode("Greece", "Vodafone Greece", "To check your balance, make a free call to 1252", "", "tag"),
                new NetworksCode("Greece", "Vodafone Greece", "Text free SMS to 1228 with the word \"balance\"", "", "tag"),


                new NetworksCode("South Georgia and the South Sandwich Islands", "", "", "", "tag"),
                new NetworksCode("Guatemala", "", "", "", "tag"),
                new NetworksCode("Guam", "", "", "", "tag"),
                new NetworksCode("Guinea-Bissau", "", "", "", "tag"),
                new NetworksCode("Guyana", "", "", "", "tag"),


                new NetworksCode("Hong Kong", "Csl", "*109#", "", "tag"),
                new NetworksCode("Hong Kong", "Three", "Dial 1753175 or press [##107#]+ [send] through your handset.", "", "tag"),
                new NetworksCode("Hong Kong", "SmarTone", "*111#", "", "tag"),


                new NetworksCode("Heard Island and McDonald Islands", "", "", "", "tag"),
                new NetworksCode("Honduras", "", "", "", "tag"),
                new NetworksCode("Croatia", "", "", "", "tag"),
                new NetworksCode("Haiti", "", "", "", "tag"),

                new NetworksCode("Hungary", "Magyar Telekom", "*102#", "", "tag"),
                new NetworksCode("Hungary", "Telenor Hungary", "send SMS to number 1000", "", "tag"),
                new NetworksCode("Hungary", "Vodafone Hungary", "Text ‘IEGYENLEG' to 171 (SMS costs 49 HUF)", "", "tag"),

                new NetworksCode("Indonesia", "Telkomsel", "*889#", "", "tag"),
                new NetworksCode("Indonesia", "IM3 Ooredoo (a.k.a. Indosat)", "*123#", "", "tag"),
                new NetworksCode("Indonesia", "3 (= Tri)", "*123#", "", "tag"),
                new NetworksCode("Indonesia", "XL Axiata", "*123#", "", "tag"),

                new NetworksCode("Ireland", "Eir", "*#100#", "", "tag"),
                new NetworksCode("Ireland", "Vodafone (Ireland)", "*174#", "", "tag"),
                new NetworksCode("Ireland", "Hutchison 3 (= Three)", "To check balance, dial 1745 for free", "", "tag"),

                new NetworksCode("Israel", "Cellcom", "*11777#", "", "tag"),
                new NetworksCode("Israel", "Partner", "*111#", "", "tag"),
                new NetworksCode("Israel", "Pelephone", "send \"b\" to 700", "", "tag"),

                new NetworksCode("Isle of Man", "", "", "", "tag"),

                new NetworksCode("India", "Bharti Airtel:", "*121*2#", "", "tag"),
                new NetworksCode("India", "Reliance Jio:", "Text \"BAL\" to 536 70", "", "tag"),
                new NetworksCode("India", "BSNL and MTNL", "*444#", "", "tag"),

                new NetworksCode("British Indian Ocean Territory", "", "", "", "tag"),

                new NetworksCode("Iraq", "", "", "", "tag"),

                new NetworksCode("Iran, Islamic Republic of", "Hamrah-e-Aval", "Call 444 for balance enquiry or top up guide", "", "tag"),
                new NetworksCode("Iran, Islamic Republic of", "Irancell", "*141*1#", "", "tag"),
                new NetworksCode("Iran, Islamic Republic of", "RighTel", "*140#", "", "tag"),


                new NetworksCode("Iceland", "", "", "", "tag"),

                new NetworksCode("Italy", "Tim", "*222#", "", "tag"),
                new NetworksCode("Italy", "Vodafone", "Browse to http://contatori.vodafone.it/ using your SIM card data connection", "", "tag"),
                new NetworksCode("Italy", "WindTre", "*133*1#", "", "tag"),
                new NetworksCode("Italy", "Iliad", "end an SMS to 400 to receive as a response the remaining credit of your SIM", "", "tag"),

                new NetworksCode("Jersey", "", "", "", "tag"),

                new NetworksCode("Jamaica", "", "", "", "tag"),

                new NetworksCode("Jordan", "Zain", "*116*2#", "", "tag"),
                new NetworksCode("Jordan", "Umniah", "*1333#", "", "FALSE"),
                new NetworksCode("Jordan", "Orange", "*155#", "", "tag"),

                new NetworksCode("Japan", "", "", "", "tag"),

                new NetworksCode("Kyrgyzstan", "", "", "", "tag"),



                new NetworksCode("Kenya", "Airtel", "#100#", "airtime", "tag"),
                new NetworksCode("Kenya", "Airtel", "*131#", "data", "tag"),
                new NetworksCode("Kenya", "Safaricom", "*144#", "airtime", "tag"),
                new NetworksCode("Kenya", "Safaricom", "*544#", "data", "tag"),
                new NetworksCode("Kenya", "Telkom", "*131#", "airtime", "tag"),
                new NetworksCode("Kenya", "Telkom", "*544#", "data", "tag"),


                new NetworksCode("Cambodia", "", "", "", "tag"),


                new NetworksCode("Kiribati", "", "", "", "tag"),


                new NetworksCode("Comoros", "", "", "", "tag"),


                new NetworksCode("Saint Kitts and Nevis", "", "", "", "tag"),


                new NetworksCode("North Korea", "", "", "", "tag"),


                new NetworksCode("South Korea", "", "", "", "tag"),


                new NetworksCode("Kuwait", "", "", "", "tag"),


                new NetworksCode("Cayman Islands", "", "", "", "tag"),


                new NetworksCode("Kazakhstan", "", "", "", "tag"),


                new NetworksCode("Lao People\'s Democratic Republic", "", "", "", "tag"),


                new NetworksCode("Lebanon", "Touch", "*220#", "", "tag"),
                new NetworksCode("Lebanon", "Alfa", "*111#", "", "tag"),


                new NetworksCode("Saint Lucia", "", "", "", "tag"),


                new NetworksCode("Liechtenstein", "", "", "", "tag"),


                new NetworksCode("Sri Lanka", "", "", "", "tag"),


                new NetworksCode("Liberia", "Mtn", "*124#", "airtime", "tag"),
                new NetworksCode("Liberia", "Mtn", "*352*7#", "data", "tag"),
                new NetworksCode("Liberia", "Orange", "*124#", "airtime", "tag"),
                new NetworksCode("Liberia", "Orange", "*352#", "data", "tag"),








                new NetworksCode("Lesotho", "Econet", "*134#", "airtime", "tag"),
                new NetworksCode("Lesotho", "Econet", "*100#", "data", "tag"),
                new NetworksCode("Lesotho", "Vodacom", "*100#", "airtime", "tag"),
                new NetworksCode("Lesotho", "Vodacom", "*111#", "data", "tag"),



                new NetworksCode("Lithuania", "", "", "", "tag"),

                new NetworksCode("Luxembourg", "", "", "", "tag"),

                new NetworksCode("Latvia", "", "", "", "tag"),

                new NetworksCode("Libyan Arab Jamahiriya", "", "", "", "tag"),

                new NetworksCode("Morocco", "Maroc Telecom", "#580#", "", "tag"),
                new NetworksCode("Morocco", "Orange", "#554#", "", "tag"),
                new NetworksCode("Morocco", "Inwi", "*120#", "", "tag"),

                new NetworksCode("Monaco", "", "", "", "tag"),

                new NetworksCode("Moldova", "", "", "", "tag"),

                new NetworksCode("Montenegro", "", "", "", "tag"),

                new NetworksCode("Saint Martin", "", "", "", "tag"),

                new NetworksCode("Madagascar", "", "", "", "tag"),

                       

                new NetworksCode("Marshall Islands", "", "", "", "tag"),

                new NetworksCode("Macedonia, The Former Yugoslav Republic of", "", "", "", "tag"),

                new NetworksCode("Mali", "Orange", "#123#", "airtime", "tag"),
                new NetworksCode("Mali", "Orange", "#101#22#", "data", "tag"),
                new NetworksCode("Mali", "Sotelma-Malitel", "*101#", "", "tag"),



                new NetworksCode("Myanmar", "", "", "", "tag"),

                new NetworksCode("Mongolia", "", "", "", "tag"),

                new NetworksCode("Macao", "CTM (MobiWeb)", "*122#", "", "tag"),
                new NetworksCode("Macao", "Three Macau", "*105#", "", "tag"),
                new NetworksCode("Macao", "SmarTone", "*137#", "", "tag"),

                new NetworksCode("Northern Mariana Islands", "", "", "", "tag"),

                new NetworksCode("Martinique", "", "", "", "tag"),

                new NetworksCode("Mauritania", "Mauritel", "#123#", "", "tag"),
                new NetworksCode("Mauritania", "Chinguitel", "*222#", "airtime", "tag"),
                new NetworksCode("Mauritania", "Chinguitel", "*590#", "data", "tag"),
                new NetworksCode("Mauritania", "Mattel", "*130#", "airtime", "tag"),
                new NetworksCode("Mauritania", "Mattel", "*167*0#", "data", "tag"),



                new NetworksCode("Montserrat", "", "", "", "tag"),

                new NetworksCode("Malta", "", "", "", "tag"),

                new NetworksCode("Mauritius", "Orange", "*122#", "airtime", "tag"),
                new NetworksCode("Mauritius", "Orange", "TEXT QUERY TO 812", "data", "tag"),
                new NetworksCode("Mauritius", "Emtel", "*122#", "", "tag"),
                new NetworksCode("Mauritius", "Chili", "*222#", "", "tag"),



                new NetworksCode("Maldives", "", "", "", "tag"),

                new NetworksCode("Malawi", "Airtel", "*137#", "airtime", "tag"),
                new NetworksCode("Malawi", "Airtel", "*304#", "data", "tag"),
                new NetworksCode("Malawi", "TNM", "", "#123#", "tag"),



                new NetworksCode("Mexico", "Telcel", "*133#", "", "tag"),
                new NetworksCode("Mexico", "Movistar", "*100#", "", "tag"),
                new NetworksCode("Mexico", "AT&T Unidos Mexico", "*777#", "", "tag"),

                new NetworksCode("Malaysia", "DiGi", "*126#", "", "tag"),
                new NetworksCode("Malaysia", "Maxis", "*122#", "", "tag"),
                new NetworksCode("Malaysia", "Celcom", "*118*1*1*2#", "", "tag"),
                new NetworksCode("Malaysia", "U Mobile", "*118#", "", "tag"),

                new NetworksCode("Mozambique", "Mcel", "*123#", "", "tag"),
                new NetworksCode("Mozambique", "Vodacom", "*100#", "", "tag"),
                new NetworksCode("Mozambique", "Movitel", "*451#", "", "tag"),



                new NetworksCode("Namibia", "*124#", "airtime", "", "tag"),
                new NetworksCode("Namibia", "*100#", "data", "", "tag"),
                new NetworksCode("Namibia", "*131#", "", "", "tag"),




                new NetworksCode("New Caledonia", "", "", "", "tag"),

                new NetworksCode("Niger", "", "", "", "tag"),


                new NetworksCode("Norfolk Island", "", "", "", "tag"),

                new NetworksCode("Nigeria", "Airtel", "*123#", "airtime", "tag"),
                new NetworksCode("Nigeria", "Airtel", "*140#", "data", "tag"),
                new NetworksCode("Nigeria", "9mobile", "*232#", "airtime", "tag"),
                new NetworksCode("Nigeria", "9mobile", "*228#", "data", "tag"),
                new NetworksCode("Nigeria", "Glo", "*124*1#", "airtime", "tag"),
                new NetworksCode("Nigeria", "Glo", "*127*0#", "data", "tag"),
                new NetworksCode("Nigeria", "Mtn", "*556#", "airtime", "tag"),
                new NetworksCode("Nigeria", "Mtn", "*559#", "data", "tag"),


                new NetworksCode("Nicaragua", "", "", "", "tag"),

                new NetworksCode("Netherlands", "", "", "", "tag"),


                new NetworksCode("Norway", "", "", "", "tag"),

                new NetworksCode("Nepal", "", "", "", "tag"),

                new NetworksCode("Nauru", "", "", "", "tag"),
 
                new NetworksCode("Niue", "", "", "", "tag"),

                new NetworksCode("New Zealand", "", "", "", "tag"),

                new NetworksCode("Oman", "Omantel", "*100#", "", "tag"),
                new NetworksCode("Oman", "Ooredoo", "Dial *141*4*9# or *141*377# (for tourist Sim)", "", "tag"),

                new NetworksCode("Panama", "Móvil", "*165#", "", "tag"),
                new NetworksCode("Panama", "Claro", "*103#", "", "tag"),
                new NetworksCode("Panama", "Digicel", "*120*1#", "", "tag"),
                new NetworksCode("Panama", "Movistar", "*123#", "", "tag"),

                new NetworksCode("Peru", "Movistar Peru", "*515#", "", "tag"),
                new NetworksCode("Peru", "Claro Peru", "Text \"SALDO\" to 777", "", "tag"),
                new NetworksCode("Peru", "Entel Peru", "*144#", "", "tag"),
                new NetworksCode("Peru", "Bitel", "*121#", "", "tag"),

                new NetworksCode("French Polynesia", "", "", "", "tag"),

                new NetworksCode("Papua New Guinea", "", "", "", "tag"),

                new NetworksCode("Philippines", "", "", "", "tag"),

                new NetworksCode("Pakistan", "", "", "", "tag"),

                new NetworksCode("Poland", "Play", "*111#", "", "tag"),
                new NetworksCode("Poland", "Orange", "*124#", "", "tag"),
                new NetworksCode("Poland", "Plus", "*101#", "", "tag"),
                new NetworksCode("Poland", "T-Mobile", "*101#", "", "tag"),

                new NetworksCode("Saint Pierre and Miquelon", "", "", "", "tag"),

                new NetworksCode("Pitcairn", "", "", "", "tag"),

                new NetworksCode("Puerto Rico", "", "", "", "tag"),

                new NetworksCode("Palestinian Territory, Occupied", "", "", "", "tag"),

                new NetworksCode("Portugal", "", "", "", "tag"),

                new NetworksCode("Palau", "", "", "", "tag"),

                new NetworksCode("Paraguay", "", "", "", "tag"),

                new NetworksCode("Qatar", "Ooredoo", "*129#", "", "tag"),
                new NetworksCode("Qatar", "Ooredoo", "*129*14#", "", "tag"),
                new NetworksCode("Qatar", "Vodafone", "100#", "", "tag"),

                new NetworksCode("Réunion", "", "", "", "tag"),

                new NetworksCode("Romania", "", "", "", "tag"),

                new NetworksCode("Serbia", "", "", "", "tag"),

                new NetworksCode("Russia", "MegaFon", "*558#", "", "tag"),
                new NetworksCode("Russia", "Mts", "#100#", "", "tag"),
                new NetworksCode("Russia", "Tele2", "*105#", "", "tag"),

                new NetworksCode("Rwanda", "Mtn", "*110#", "airtime", "tag"),
                new NetworksCode("Rwanda", "Mtn", "*345*5#", "data", "tag"),
                new NetworksCode("Rwanda", "Airtel-Tigo", "*131#", "airtime", "tag"),
                new NetworksCode("Rwanda", "Airtel-Tigo", "*131*3#", "data", "tag"),



                new NetworksCode("Saudi Arabia", "STC", "Text 8888 to 900", "", "tag"),
                new NetworksCode("Saudi Arabia", "Mobily", "*1411#", "", "tag"),
                new NetworksCode("Saudi Arabia", "Zain", "Text empty message to 959", "", "tag"),
                new NetworksCode("Saudi Arabia", "Friendi", "Call 959 and follow the instructions", "", "tag"),
                new NetworksCode("Saudi Arabia", "Virgin", "*102#", "", "tag"),

                new NetworksCode("Solomon Islands", "", "", "", "tag"),

                new NetworksCode("Seychelles", "Airtel", "*220#", "airtime", "tag"),
                new NetworksCode("Seychelles", "Airtel", "*220*3#", "data", "tag"),
                new NetworksCode("Seychelles", "Cable & Wireless", "*125#", "", "tag"),



                new NetworksCode("Sudan", "Mtn", "*141#", "airtime", "tag"),
                new NetworksCode("Sudan", "Mtn", "*400#", "data", "tag"),
                new NetworksCode("Sudan", "Zain", "*888#", "airtime", "tag"),
                new NetworksCode("Sudan", "Zain", "*101*3#", "data", "tag"),

                new NetworksCode("Sweden", "", "", "", "tag"),

                new NetworksCode("Singapore", "Singtel Mobile", "*139#", "", "tag"),
                new NetworksCode("Singapore", "StarHub", "*113#", "", "tag"),
                new NetworksCode("Singapore", "M1", "#100*2*1#", "", "tag"),
                new NetworksCode("Singapore", "TPG Telecom", "Visit the Website: https://www.tpgtelecom.com.sg/", "", "tag"),

                new NetworksCode("Saint Helena, Ascension and Tristan Da Cunha", "", "", "", "tag"),

                new NetworksCode("Slovenia", "", "", "", "tag"),

                new NetworksCode("Svalbard and Jan Mayen", "", "", "", "tag"),

                new NetworksCode("Slovakia", "", "", "", "tag"),

                new NetworksCode("Sierra Leone", "", "", "", "tag"),
                new NetworksCode("Sierra Leone", "", "", "", "tag"),
                new NetworksCode("Sierra Leone", "", "", "", "tag"),
                new NetworksCode("Sierra Leone", "", "", "", "tag"),
                new NetworksCode("Sierra Leone", "", "", "", "tag"),
                new NetworksCode("Sierra Leone", "", "", "", "tag"),



                new NetworksCode("San Marino", "", "", "", "tag"),

                new NetworksCode("Senegal", "Expresso", "*222#", "airtime", "tag"),
                new NetworksCode("Senegal", "Expresso", "*222*266#", "data", "tag"),
                new NetworksCode("Senegal", "Orange", "#123#", "", "tag"),
                new NetworksCode("Senegal", "Tigo", "#150#", "", "tag"),



                new NetworksCode("Somalia", "", "", "", "tag"),

                new NetworksCode("Suriname", "", "", "", "tag"),

                new NetworksCode("South Sudan", "", "", "", "tag"),

                new NetworksCode("Sao Tome and Principe", "Unitel", "*105#", "", "tag"),
                new NetworksCode("Sao Tome and Principe", "Cst", "*123*1#", "airtime", "tag"),
                new NetworksCode("Sao Tome and Principe", "Cst", "*123*2#", "data", "tag"),



                new NetworksCode("El Salvador", "", "", "", "tag"),

                new NetworksCode("Sint Maarten", "", "", "", "tag"),

                new NetworksCode("Syrian Arab Republic", "", "", "", "tag"),

                new NetworksCode("Swaziland", "Mtn", "*556#", "airtime", "tag"),
                new NetworksCode("Swaziland", "Mtn", "*686#", "data", "tag"),



                new NetworksCode("Turks and Caicos Islands", "", "", "", "tag"),

                new NetworksCode("Chad", "Tigo Chad", "*100#", "airtime", "tag"), //TD
                new NetworksCode("Chad", "Tigo Chad", "*128#", "data", "tag"), //TD
                new NetworksCode("Chad", "Airtel", "*137#", "airtime", "tag"), //TD
                new NetworksCode("Chad", "Airtel", "*342*4#", "data", "tag"), //TD



                new NetworksCode("French Southern Territories", "", "", "", "tag"),

                new NetworksCode("Togo", "Togocel", "*444#", "airtime", "tag"),
                new NetworksCode("Togo", "Togocel", "*104*2#", "data", "tag"),
                new NetworksCode("Togo", "Moov", "*400#", "", "tag"),


                new NetworksCode("Thailand", "AIS", "*121#", "", "tag"),
                new NetworksCode("Thailand", "DTAC", "*101*1*9#", "", "tag"),
                new NetworksCode("Thailand", "True Move H", "check your remaining balance by 9302", "", "tag"),
                new NetworksCode("Thailand", "MY", "*902#", "", "tag"),

                new NetworksCode("Tajikistan", "", "", "", "tag"),

                new NetworksCode("Tokelau", "", "", "", "tag"),

                new NetworksCode("East Timor", "", "", "", "tag"),

                new NetworksCode("Turkmenistan", "", "", "", "tag"),

                new NetworksCode("Tunisia", "Ooredoo", "*4444#", "", "tag"),
                new NetworksCode("Tunisia", "ooredoo", "*122#", "", "tag"),
                new NetworksCode("Tunisia", "Orange", "*101#", "", "tag"),

                new NetworksCode("Tonga", "", "", "", "tag"),

                new NetworksCode("Turkey", "Turkcell", "*123#", "", "tag"),
                new NetworksCode("Turkey", "Vodafone", "*123#", "", "tag"),
                new NetworksCode("Turkey", "Türk Telekom", "*123#", "", "tag"),

                new NetworksCode("Trinidad and Tobago", "", "", "", "tag"),

                new NetworksCode("Tuvalu", "", "", "", "tag"),

                new NetworksCode("Taiwan", "", "", "", "tag"),

                new NetworksCode("Tanzania, United Republic of", "Vodacom", "*102#", "airtime", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Vodacom", "*149*01#", "data", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Airtel", "*102#", "airtime", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Airtel", "148*22#", "data", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Tigo", "*102#", "airtime", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Tigo", "*148*00#", "data", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Zantel", "*102#", "airtime", "tag"),
                new NetworksCode("Tanzania, United Republic of", "Zantel", "*149*07#", "data", "tag"),



                new NetworksCode("Ukraine", "", "", "", "tag"),

                new NetworksCode("Uganda", "Airtel", "*131#", "airtime", "tag"),
                new NetworksCode("Uganda", "Airtel", "*175*4#", "data", "tag"),
                new NetworksCode("Uganda", "Mtn", "*131#", "airtime", "tag"),
                new NetworksCode("Uganda", "Mtn", "*150*1#", "data", "tag"),
                new NetworksCode("Uganda", "Africell", "*131#", "airtime", "tag"),
                new NetworksCode("Uganda", "Africell", "*133#", "data", "tag"),
                new NetworksCode("Uganda", "Smart Telecom", "*131#", "airtime", "tag"),
                new NetworksCode("Uganda", "Smart Telecom", "*100#", "data", "tag"),
                new NetworksCode("Uganda", "UTL Telecel", "*131#", "airtime", "tag"),
                new NetworksCode("Uganda", "UTL Telecel", "*160#", "data", "tag"),


                new NetworksCode("U.S. Minor Outlying Islands", "", "", "", "tag"),

                new NetworksCode("United States", "Verizon Wireless", "#646#", "", "tag"),
                new NetworksCode("United States", "Verizon Wireless", "#3282#", "", "tag"),
                new NetworksCode("United States", "T – Mobile", "#646#", "", "tag"),
                new NetworksCode("United States", "T – Mobile", "#932#", "", "tag"),
                new NetworksCode("United States", "AT&T", "#646#", "", "tag"),
                new NetworksCode("United States", "AT&T", "*3282#", "", "tag"),


                new NetworksCode("Uruguay", "", "", "", "tag"),

                new NetworksCode("Uzbekistan", "", "", "", "tag"),

                new NetworksCode("Holy See (Vatican City State)", "", "", "", "tag"),

                new NetworksCode("Saint Vincent and the Grenadines", "", "", "", "tag"),

                new NetworksCode("Venezuela, Bolivarian Republic of", "", "", "", "tag"),

                new NetworksCode("Virgin Islands, British", "", "", "", "tag"),

                new NetworksCode("Virgin Islands, U.S.", "", "", "", "tag"),
                new NetworksCode("Viet Nam", "", "", "", "tag"),

                new NetworksCode("Vanuatu", "", "", "", "tag"),

                new NetworksCode("Wallis and Futuna", "", "", "", "tag"),

                new NetworksCode("Samoa", "", "", "", "tag"),

                new NetworksCode("Kosovo", "", "", "", "tag"),

                new NetworksCode("Yemen", "", "", "", "tag"),

                new NetworksCode("Mayotte", "", "", "", "tag"),

                new NetworksCode("South Africa", "Vodacom", "*100#", "airtime", "tag"),
                new NetworksCode("South Africa", "Vodacom", "*135#", "data", "tag"),
                new NetworksCode("South Africa", "Mtn", "*136*1#", "airtime", "tag"),
                new NetworksCode("South Africa", "Mtn", "*136#", "data", "tag"),
                new NetworksCode("South Africa", "Cell C", "BAL TO 14302", "airtime", "tag"),
                new NetworksCode("South Africa", "Cell C", "*101#", "data", "tag"),
                new NetworksCode("South Africa", "8ta", "*100#", "airtime", "tag"),
                new NetworksCode("South Africa", "8ta", "*188#", "data", "tag"),


                new NetworksCode("Zambia", "Airtel", "*114#", "airtime", "tag"),
               new NetworksCode("Zambia", "Airtel", "*121#", "data", "tag"),
               new NetworksCode("Zambia", "Mtn", "*114#", "airtime", "tag"),
               new NetworksCode("Zambia", "Mtn", "*335#", "data", "tag"),
               new NetworksCode("Zambia", "Zamtel", "*113#v", "airtime", "tag"),
               new NetworksCode("Zambia", "Zamtel", "*335#", "data", "tag"),


                new NetworksCode("Zimbabwe", "Econet", "*125#", "airtime", "tag"),
                new NetworksCode("Zimbabwe", "Econet", "*143#", "data", "tag"),
                 new NetworksCode("Zimbabwe", "Telcel", "*122#", "airtime", "tag"),
                 new NetworksCode("Zimbabwe", "Telcel", "*148#", "data", "tag"),
                 new NetworksCode("Zimbabwe", "Netone", "*134#", "", "tag"),





        };
    }
}
