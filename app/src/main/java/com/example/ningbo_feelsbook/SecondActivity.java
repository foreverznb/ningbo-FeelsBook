package com.example.ningbo_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The type Second activity.
 */
public class SecondActivity extends AppCompatActivity {
    /**
     * The constant Position.
     */
    public static final String Position = "Position";
    private static final String FILENAME = "file.sav";
    private ListView oldEmotionList_2;
    private ArrayList<Emotion> emotions_2 = new ArrayList<Emotion>();
    private ArrayAdapter<Emotion> adapter_2;
    private Integer angry_count_1=0;
    private Integer fear_count_1=0;
    private Integer joy_count_1=0;
    private Integer love_count_1=0;
    private Integer sad_count_1=0;
    private Integer surprised_count_1=0;
    private TextView angry_count;
    private TextView fear_count;
    private TextView joy_count;
    private TextView love_count;
    private TextView sad_count;
    private TextView surprised_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        oldEmotionList_2 = (ListView) findViewById(R.id.oldEmotionList_2);
        angry_count = (TextView) findViewById(R.id.angry_count);
        fear_count = (TextView) findViewById(R.id.fear_count);
        joy_count = (TextView) findViewById(R.id.joy_count);
        love_count = (TextView) findViewById(R.id.love_count);
        sad_count = (TextView) findViewById(R.id.sad_count);
        surprised_count = (TextView) findViewById(R.id.surprised_count);

        oldEmotionList_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra(Position,position);
                startActivity(intent);
            }
        });
    }


    protected void onStart() {

        super.onStart();
        loadFromFile();

        adapter_2 = new ArrayAdapter<Emotion>(this,
                R.layout.list_item, emotions_2);

        oldEmotionList_2.setAdapter(adapter_2);
        Counting();
        angry_count.setText(angry_count_1.toString());
        fear_count.setText(fear_count_1.toString());
        joy_count.setText(joy_count_1.toString());
        love_count.setText(love_count_1.toString());
        sad_count.setText(sad_count_1.toString());
        surprised_count.setText(surprised_count_1.toString());


    }

    private void Counting(){
        Integer maximum = emotions_2.size();
        for(int i = 0; i < maximum; i++){
            String emotion_count = emotions_2.get(i).getEmotion();
            if(emotion_count.compareTo("angry")==0){
                angry_count_1 += 1;
            }
            else if(emotion_count.compareTo("fear")==0){
                fear_count_1 += 1;
            }
            else if(emotion_count.compareTo("joy")==0){
                joy_count_1 += 1;
            }
            else if(emotion_count.compareTo("love")==0){
                love_count_1 += 1;
            }
            else if(emotion_count.compareTo("sad")==0){
                sad_count_1 += 1;
            }
            else if(emotion_count.compareTo("surprise")==0){
                surprised_count_1 += 1;
            }

        }

    }

    private void  loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type listEmotionType=new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotions_2=gson.fromJson(reader,listEmotionType);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
