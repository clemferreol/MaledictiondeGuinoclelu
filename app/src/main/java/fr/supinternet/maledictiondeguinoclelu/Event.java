package fr.supinternet.maledictiondeguinoclelu;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by clementineferreol on 05/12/2017.
 */

public class Event implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("content")
    private String content;

    @SerializedName("actions")
    private ArrayList<Action> actions;

    @SerializedName("answers")
    private ArrayList<Answer> answers;

    protected Event(Parcel in) {
        id = in.readString();
        content = in.readString();
        actions = in.createTypedArrayList(Action.CREATOR);
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(content);
        dest.writeTypedList(actions);
        dest.writeTypedList(answers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", actions=" + actions +
                ", answers=" + answers +
                '}';
    }
}

