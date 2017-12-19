package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EventsActivity extends AppCompatActivity {

    private TextView titleevent;
    private TextView contentevent;
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
        action1 = (Button) findViewById(R.id.action1);
        action2 = (Button) findViewById(R.id.action2);
        action3 = (Button) findViewById(R.id.action3);

        Place place = getIntent().getParcelableExtra(STRING_KEY);

       //StringBuilder builder = new StringBuilder();
        for(int l=0; l<=place.getEvent().size(); l++){
            Random r = new Random();
            final int i1 = r.nextInt((place.getEvent().size() -1) - 0) + 0;
            final Event event = place.getEvent().get(i1);
            Log.i("EventsActivity", "Event:" + event);


            titleevent.setText(event.getId());
            contentevent.setText(event.getContent());

            action1.setText(event.getActions().get(0).getContent());


            action1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Answer answer = event.getAnswers().get(0).get(i1);
                    Toast.makeText(EventsActivity.this, answer.getMessage(), Toast.LENGTH_LONG).show();
                    //afficher réponse de manière aléatoire et changer HP
                    //Intent intent = new Intent(EventsActivity.this, Event2Activity.class);
                    //startActivity(intent);
                }
            });

            action2.setText(event.getActions().get(1).getContent());


            action3.setText(event.getActions().get(2).getContent());
            break;
        }
    }
}
