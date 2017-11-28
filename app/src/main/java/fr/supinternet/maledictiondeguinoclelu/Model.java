package fr.supinternet.maledictiondeguinoclelu;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

/**
 * Created by clementineferreol on 27/11/2017.
 */

public class Model {

    @SerializedName("data")
    public ArrayList<MyObject> data;

    static public class MyObject {
        @SerializedName("lieu")
        public String lieu;
    }
}
