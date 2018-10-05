package com.example.ningbo_feelsbook;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.gson.Gson;


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private ArrayAdapter<Emotion> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bodyText = (EditText) findViewById(R.id.body);
        Button historyButton = (Button) findViewById(R.id.history);
        ImageButton joyButton = (ImageButton) findViewById(R.id.joy);
        ImageButton fearButton = (ImageButton) findViewById(R.id.fear);
        ImageButton angryButton = (ImageButton) findViewById(R.id.angry);
        ImageButton loveButton = (ImageButton) findViewById(R.id.love);
        ImageButton sadButton = (ImageButton) findViewById(R.id.sad);
        ImageButton surprisedButton = (ImageButton) findViewById(R.id.suprised);


        fearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "fear";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

        angryButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "angry";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

        joyButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "joy";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

        loveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "love";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

        sadButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "sad";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

        surprisedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String text = bodyText.getText().toString();
                bodyText.setText("");
                Emotion newEmotion = new Emotion();
                String emotion_name = "surprise";
                newEmotion.setMessage(text);
                newEmotion.setDate(new Date());
                newEmotion.setEmotion(emotion_name);
                emotions.add(newEmotion);
                saveInFile();
                Context context = getApplicationContext();
                CharSequence toast_show = "Your emotion has been saved!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, toast_show, duration);
                toast.show();

            }
        });

    }

    /**
     * To history.
     *
     * @param view the view
     */
    public void toHistory(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emotions, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
