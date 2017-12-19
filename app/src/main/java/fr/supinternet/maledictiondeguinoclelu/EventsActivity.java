package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class EventsActivity extends AppCompatActivity {

    private TextView titleevent;
    private TextView contentevent;
    private TextView display;
    private Button action1;
    private Button action2;
    private Button action3;

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
        display = (TextView) findViewById(R.id.display);
        action1 = (Button) findViewById(R.id.action1);
        action2 = (Button) findViewById(R.id.action2);
        action3 = (Button) findViewById(R.id.action3);

        Place place = getIntent().getParcelableExtra(STRING_KEY);

       // StringBuilder builder = new StringBuilder();
        for(int l=0; l<=place.getEvent().size(); l++){
            Random r = new Random();
            int i1 = r.nextInt((place.getEvent().size() -1) - 0) + 0;
            titleevent.setText(place.getEvent().get(0).getId());
            contentevent.setText(place.getEvent().get(0).getContent());
            action1.setText(place.getEvent().get(0).getActions().get(0).getContent());
            action1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //afficher réponse de manière aléatoire et changer HP
                    //Intent intent = new Intent(EventsActivity.this, GameActivity.class);
                    //startActivity(intent);
                }
            });
            action2.setText(place.getEvent().get(0).getActions().get(1).getContent());
            action3.setText(place.getEvent().get(0).getActions().get(2).getContent());
            break;
        }
    }
}
