package fr.supinternet.maledictiondeguinoclelu;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clementineferreol on 27/11/2017.
 */

public class Model {

    public ArrayList<Lieu> map;

    public ArrayList<Lieu> getMap() {
        return map;
    }

    public void setMap(ArrayList<Lieu> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Model{" +
                "map=" + map +
                '}';
    }

    static public class Lieu {
        @SerializedName("id")
        public String id;

        @SerializedName("event")
        public ArrayList<Event> event;

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
    }

    static public class Event{
        @SerializedName("id")
        public String id;

        @SerializedName("content")
        public String content;

        @SerializedName("actions")
        public ArrayList<Action> actions;

        @SerializedName("answers")
        ArrayList<ArrayList<Answer>> answers;

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

        public ArrayList<ArrayList<Answer>> getAnswers() {
            return answers;
        }

        public void setAnswers(ArrayList<ArrayList<Answer>> answers) {
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

    static public class Action {
        @SerializedName("answer_id")
        public int answerId;

        public String content;

        public int getAnswerId() {
            return answerId;
        }

        public void setAnswerId(int answerId) {
            this.answerId = answerId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "answerId='" + answerId + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    static class Answer {
        @SerializedName("message")
        public String message;

        @SerializedName("hp")
        public int hp;

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
}
