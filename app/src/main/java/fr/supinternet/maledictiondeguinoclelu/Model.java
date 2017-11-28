package fr.supinternet.maledictiondeguinoclelu;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

/**
 * Created by clementineferreol on 27/11/2017.
 */

public class Model {

    @SerializedName("data")
    public ArrayList<MyObject> list;

    static public class MyObject {
        @SerializedName("name")
        public String name;
        @SerializedName("age")
        public int age;
    }
}
