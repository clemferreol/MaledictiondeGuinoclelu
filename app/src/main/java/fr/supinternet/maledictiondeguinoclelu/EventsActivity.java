package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
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
    public TextView hp;
    private Button action1;
    private Button action2;
    private Button action3;
    private Button backButton;

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
        hp = (TextView) findViewById(R.id.hp);
        action1 = (Button) findViewById(R.id.action1);
        action2 = (Button) findViewById(R.id.action2);
        action3 = (Button) findViewById(R.id.action3);
        backButton = (Button) findViewById(R.id.BACKBUTTON);
        final int intbut = 40;

        hp.setText(String.valueOf(intbut));


        Place place = getIntent().getParcelableExtra(STRING_KEY);

        // StringBuilder builder = new StringBuilder();
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
                    Random rn = new Random();
                    int random = rn.nextInt(8 - 0 + 1) + 0;
                    final Answer answer = event.getAnswers().get(random);
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(EventsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(EventsActivity.this);
                    }
                    builder.setTitle("Message : ")
                            .setMessage(answer.getMessage() + "\n" + "Votre HP : " + answer.getHp())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if(answer.getHp() == 1){
                                        hp.setText(String.valueOf(intbut + 1));
                                    }
                                    if(answer.getHp() == 0){
                                        hp.setText(String.valueOf(intbut + 0));
                                    }

                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });

            action2.setText(event.getActions().get(1).getContent());

            action2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Random rn = new Random();
                    int random = rn.nextInt(8 - 0 + 1) + 0;
                    Answer answer = event.getAnswers().get(random);
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(EventsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(EventsActivity.this);
                    }
                    builder.setTitle("Message : ")
                            .setMessage(answer.getMessage() + "\n" + "Votre HP : " + answer.getHp())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });

            action3.setText(event.getActions().get(2).getContent());
            action3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Random rn = new Random();
                    int random = rn.nextInt(8 - 0 + 1) + 0;
                    Answer answer = event.getAnswers().get(random);
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(EventsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(EventsActivity.this);
                    }
                    builder.setTitle("Message : ")
                            .setMessage(answer.getMessage() + "\n" + "Votre HP : " + answer.getHp())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
            backButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(EventsActivity.this, GameActivity.class);
                    startActivity(intent);

                }
            });
            break;
        }

    }
}
