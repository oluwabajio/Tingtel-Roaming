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

                new NetworksCode("United Arab Emirates", "Airtime Balance", "*123#", "This service enables you to check your current airtime balance which is available for calls", "tag"),
                new NetworksCode("Afghanistan", "", "", "", "tag"),
                new NetworksCode("Antigua and Barbuda", "", "", "", "tag"),
                new NetworksCode("Anguilla", "", "", "", "tag"),
                new NetworksCode("Albania", "", "", "", "tag"),
                new NetworksCode("Armenia", "", "", "", "tag"),

                new NetworksCode("Angola", "Unitel", "*111#", "", "tag"),
                new NetworksCode("Angola", "Movicell", "*196#", "airtime", "tag"),
                new NetworksCode("Angola", "Movicell", "*200#", "data", "tag"),


                new NetworksCode("Antarctica", "", "", "", "tag"),
                new NetworksCode("Argentina", "", "", "", "tag"),
                new NetworksCode("AmericanSamoa", "", "", "", "tag"),
                new NetworksCode("Austria", "", "", "", "tag"),
                new NetworksCode("Australia", "", "", "", "tag"),
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
                new NetworksCode("Bahrain", "", "", "", "tag"),


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
                new NetworksCode("Brazil", "", "", "", "tag"),
                new NetworksCode("Bahamas", "", "", "", "tag"),
                new NetworksCode("Bhutan", "", "", "", "tag"),
                new NetworksCode("Bouvet Island", "", "", "", "tag"),


                new NetworksCode("Botswana", "Mascom", "*167#", "airtime", "tag"),
                new NetworksCode("Botswana", "Mascom", "*123*4#", "data", "tag"),
                new NetworksCode("Botswana", "Orange", "*121#", "", "tag"),


                new NetworksCode("Belarus", "", "", "", "tag"),
                new NetworksCode("Belize", "", "", "", "tag"),
                new NetworksCode("Canada", "", "", "", "tag"),
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
                new NetworksCode("Chile", "", "", "", "tag"),


                new NetworksCode("Cameroon", "Mtn", "*155#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Mtn", "*157*99#", "data", "tag"),
                new NetworksCode("Cameroon", "Orange", "#123#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Orange", "*145*1#", "data", "tag"),
                new NetworksCode("Cameroon", "Nexttel", "*801#", "airtime", "tag"),
                new NetworksCode("Cameroon", "Nexttel", "*865*1#", "data", "tag"),
                new NetworksCode("Cameroon", "Camtel", "*865*1#", "", "tag"),


                new NetworksCode("China", "", "", "", "tag"),
                new NetworksCode("Colombia", "", "", "", "tag"),
                new NetworksCode("Costa Rica", "", "", "", "tag"),
                new NetworksCode("Cuba", "", "", "", "tag"),


                new NetworksCode("Cape Verde", "Cv Movel", "*123#", "", "tag"),
                new NetworksCode("Cape Verde", "Unitel", "*707#", "", "tag"),


                new NetworksCode("Curacao", "", "", "", "tag"),
                new NetworksCode("Christmas Island", "", "", "", "tag"),
                new NetworksCode("Cyprus", "", "", "", "tag"),
                new NetworksCode("Czech Republic", "", "", "", "tag"),
                new NetworksCode("Germany", "", "", "", "tag"),


                new NetworksCode("Djibouti", "Evatis", "*168#", "airtime", "tag"),
                new NetworksCode("Djibouti", "Evatis", "*165#", "data", "tag"),


                new NetworksCode("Dominica", "", "", "", "tag"),
                new NetworksCode("Dominican Republic", "", "", "", "tag"),
                new NetworksCode("Algeria", "", "", "", "tag"),
                new NetworksCode("Ecuado", "", "", "", "tag"),
                new NetworksCode("Estonia", "", "", "", "tag"),
                new NetworksCode("Egypt", "", "", "", "tag"),
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


                new NetworksCode("Greece", "", "", "", "tag"),
                new NetworksCode("South Georgia and the South Sandwich Islands", "", "", "", "tag"),
                new NetworksCode("Guatemala", "", "", "", "tag"),
                new NetworksCode("Guam", "", "", "", "tag"),
                new NetworksCode("Guinea-Bissau", "", "", "", "tag"),
                new NetworksCode("Guyana", "", "", "", "tag"),
                new NetworksCode("Hong Kong", "", "", "", "tag"),
                new NetworksCode("Heard Island and McDonald Islands", "", "", "", "tag"),
                new NetworksCode("Honduras", "", "", "", "tag"),
                new NetworksCode("Croatia", "", "", "", "tag"),
                new NetworksCode("Haiti", "", "", "", "tag"),
                new NetworksCode("Hungary", "", "", "", "tag"),

                new NetworksCode("Indonesia", "", "", "", "tag"),

                new NetworksCode("Ireland", "", "", "", "tag"),

                new NetworksCode("Israel", "", "", "", "tag"),

                new NetworksCode("Isle of Man", "", "", "", "tag"),

                new NetworksCode("India", "", "", "", "tag"),

                new NetworksCode("British Indian Ocean Territory", "", "", "", "tag"),

                new NetworksCode("Iraq", "", "", "", "tag"),

                new NetworksCode("Iran, Islamic Republic of", "", "", "", "tag"),

                new NetworksCode("Iceland", "", "", "", "tag"),

                new NetworksCode("Italy", "Tim", "*222#", "", "tag"),
                new NetworksCode("Italy", "Vodafone", "Browse to http://contatori.vodafone.it/ using your SIM card data connection", "", "tag"),
                new NetworksCode("Italy", "WindTre", "*133*1#", "", "tag"),
                new NetworksCode("Italy", "Iliad", "end an SMS to 400 to receive as a response the remaining credit of your SIM", "", "tag"),

                new NetworksCode("Jersey", "", "", "", "tag"),

                new NetworksCode("Jamaica", "", "", "", "tag"),

                new NetworksCode("Jordan", "", "", "", "tag"),

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


                new NetworksCode("Lebanon", "", "", "", "tag"),


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

                new NetworksCode("Morocco", "", "", "", "tag"),

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

                new NetworksCode("Macao", "", "", "", "tag"),

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



                new NetworksCode("Mexico", "", "", "", "tag"),

                new NetworksCode("Malaysia", "", "", "", "tag"),

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

                new NetworksCode("Oman", "", "", "", "tag"),

                new NetworksCode("Panama", "", "", "", "tag"),

                new NetworksCode("Peru", "", "", "", "tag"),

                new NetworksCode("French Polynesia", "", "", "", "tag"),

                new NetworksCode("Papua New Guinea", "", "", "", "tag"),

                new NetworksCode("Philippines", "", "", "", "tag"),

                new NetworksCode("Pakistan", "", "", "", "tag"),

                new NetworksCode("Poland", "", "", "", "tag"),

                new NetworksCode("Saint Pierre and Miquelon", "", "", "", "tag"),

                new NetworksCode("Pitcairn", "", "", "", "tag"),

                new NetworksCode("Puerto Rico", "", "", "", "tag"),

                new NetworksCode("Palestinian Territory, Occupied", "", "", "", "tag"),

                new NetworksCode("Portugal", "", "", "", "tag"),

                new NetworksCode("Palau", "", "", "", "tag"),

                new NetworksCode("Paraguay", "", "", "", "tag"),

                new NetworksCode("Qatar", "", "", "", "tag"),

                new NetworksCode("Réunion", "", "", "", "tag"),

                new NetworksCode("Romania", "", "", "", "tag"),

                new NetworksCode("Serbia", "", "", "", "tag"),

                new NetworksCode("Russia", "", "", "", "tag"),

                new NetworksCode("Rwanda", "Mtn", "*110#", "airtime", "tag"),
                new NetworksCode("Rwanda", "Mtn", "*345*5#", "data", "tag"),
                new NetworksCode("Rwanda", "Airtel-Tigo", "*131#", "airtime", "tag"),
                new NetworksCode("Rwanda", "Airtel-Tigo", "*131*3#", "data", "tag"),



                new NetworksCode("Saudi Arabia", "", "", "", "tag"),

                new NetworksCode("Solomon Islands", "", "", "", "tag"),

                new NetworksCode("Seychelles", "Airtel", "*220#", "airtime", "tag"),
                new NetworksCode("Seychelles", "Airtel", "*220*3#", "data", "tag"),
                new NetworksCode("Seychelles", "Cable & Wireless", "*125#", "", "tag"),



                new NetworksCode("Sudan", "Mtn", "*141#", "airtime", "tag"),
                new NetworksCode("Sudan", "Mtn", "*400#", "data", "tag"),
                new NetworksCode("Sudan", "Zain", "*888#", "airtime", "tag"),
                new NetworksCode("Sudan", "Zain", "*101*3#", "data", "tag"),

                new NetworksCode("Sweden", "", "", "", "tag"),

                new NetworksCode("Singapore", "", "", "", "tag"),

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


                new NetworksCode("Thailand", "", "", "", "tag"),

                new NetworksCode("Tajikistan", "", "", "", "tag"),

                new NetworksCode("Tokelau", "", "", "", "tag"),

                new NetworksCode("East Timor", "", "", "", "tag"),

                new NetworksCode("Turkmenistan", "", "", "", "tag"),

                new NetworksCode("Tunisia", "", "", "", "tag"),

                new NetworksCode("Tonga", "", "", "", "tag"),

                new NetworksCode("Turkey", "", "", "", "tag"),

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
