package fr.supinternet.maledictiondeguinoclelu;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by clementineferreol on 05/12/2017.
 */


public class Place implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("event")
    private ArrayList<Event> event;

    protected Place(Parcel in) {
        id = in.readString();
        event = in.createTypedArrayList(Event.CREATOR);
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Event> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "id='" + id + '\'' +
                ", event=" + event +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeTypedList(event);
    }
}
