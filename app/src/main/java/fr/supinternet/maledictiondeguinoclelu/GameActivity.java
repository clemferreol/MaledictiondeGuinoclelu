package fr.supinternet.maledictiondeguinoclelu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Gson gson = new Gson();
        try {
            Model model = gson.fromJson(readJson(),  Model.class);

            model.data.toString();
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
