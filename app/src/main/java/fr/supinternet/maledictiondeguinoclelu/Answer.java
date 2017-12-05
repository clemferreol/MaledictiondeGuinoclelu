package fr.supinternet.maledictiondeguinoclelu;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by clementineferreol on 05/12/2017.
 */


class Answer implements Parcelable {
    @SerializedName("message")
    private String message;

    @SerializedName("hp")
    private int hp;

    protected Answer(Parcel in) {
        message = in.readString();
        hp = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeInt(hp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "message='" + message + '\'' +
                ", hp=" + hp +
                '}';
    }
}
