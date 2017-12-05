package fr.supinternet.maledictiondeguinoclelu;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

/**
 * Created by clementineferreol on 27/11/2017.
 */

public class Model implements Parcelable {

    public ArrayList<Place> map;

    protected Model(Parcel in) {
        map = in.createTypedArrayList(Place.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(map);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public ArrayList<Place> getMap() {
        return map;
    }

    public void setMap(ArrayList<Place> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Model{" +
                "map=" + map +
                '}';
    }

}