package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class EventsActivity extends AppCompatActivity {

    private TextView titleevent;
    private TextView contentevent;
    private TextView actionevent;

    public static final String STRING_KEY = "Lieu_list";

    public static void startActivity(Context context, Place place){
        Intent intent = new Intent(context, EventsActivity.class);
        intent.putExtra(EventsActivity.STRING_KEY, place);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        titleevent = (TextView) findViewById(R.id.eventtitle);
        contentevent = (TextView) findViewById(R.id.eventContent);
        actionevent = (TextView) findViewById(R.id.eventAction);

        Place place = getIntent().getParcelableExtra(STRING_KEY);

        StringBuilder builder = new StringBuilder();
        for(int l=0; l<=place.getEvent().size(); l++){
            Random r = new Random();
            int i1 = r.nextInt((place.getEvent().size() -1) - 0) + 0;
            titleevent.setText(place.getEvent().get(i1).getId());
            contentevent.setText(place.getEvent().get(i1).getContent());
            actionevent.setText(builder.append(place.getEvent().get(i1).getActions()));

            break;
        }

    }
}
