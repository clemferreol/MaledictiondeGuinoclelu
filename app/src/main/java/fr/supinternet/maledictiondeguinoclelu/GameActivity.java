package fr.supinternet.maledictiondeguinoclelu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new Gson();
        try {
            Model model = gson.fromJson(readJson(),  Model.class);
            Log.i("GameActivity", "json " + readJson());
            Log.i("GameActivity", "model " + model.toString());
            model.getMap().get(0).getId();
            adapter = new Adapter(this, model.getMap());
            recyclerView.setAdapter(adapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readJson() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.events);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf))!= -1){
            bos.write(buf, 0, len);
        }

        bos.close();
        inputStream.close();

        return  bos.toString();
    }

}
