package com.example.ningbo_feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * The type Third activity.
 */
public class ThirdActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    /**
     * The Emotions 2.
     */
    public ArrayList<Emotion> emotions_2 = new ArrayList<Emotion>();
    /**
     * The Adapter 2.
     */
    public ArrayAdapter<Emotion> adapter_2;
    private EditText message;
    /**
     * The Change date.
     */
    public EditText change_date;
    private EditText change_time;
    private TextView emotion;
    /**
     * The Position.
     */
    public Integer position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        position = intent.getIntExtra("Position", 0);
        Button delete_Button = (Button) findViewById(R.id.delete);
        Button save_Button = (Button) findViewById(R.id.save_2);
        message = (EditText) findViewById(R.id.message);
        emotion = (TextView) findViewById(R.id.emotion);
        change_date = (EditText) findViewById(R.id.change_date);
        change_date.setInputType(InputType.TYPE_NULL);
        change_time = (EditText) findViewById(R.id.change_time);
        change_time.setInputType(InputType.TYPE_NULL);
        change_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog();
                }
            }
        });
        change_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        change_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog();
                }
            }
        });
        change_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        delete_Button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                adapter_2.remove(adapter_2.getItem(position));
                saveInFile();
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        save_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Emotion newEmotion = new Emotion();
                String date_get = change_date.getText().toString();
                String time_get = change_time.getText().toString();
                String new_date = " " + date_get + " " + time_get;
                Date new_date_1 = new Date();
                try {
                    SimpleDateFormat date_combine = new SimpleDateFormat(" yyyy/MM/dd HH:mm:SS");
                    new_date_1 = date_combine.parse(new_date);
                } catch (java.text.ParseException e) {
                }
                String new_message = message.getText().toString();
                String new_emotion = emotion.getText().toString();
                newEmotion.setMessage(new_message);
                newEmotion.setDate(new_date_1);
                newEmotion.setEmotion(new_emotion);
                adapter_2.remove(adapter_2.getItem(position));
                adapter_2.add(newEmotion);
                adapter_2.notifyDataSetChanged();
                Sorting();

                saveInFile();
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });


    }


    private void Sorting() {
        Collections.sort(emotions_2, new Comparator<Emotion>() {
            @Override
            public int compare(Emotion o1, Emotion o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        adapter_2.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {

        super.onStart();

        loadFromFile();

        adapter_2 = new ArrayAdapter<Emotion>(this,
                R.layout.list_item, emotions_2);
        setText_bar();

    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();


            Type listEmotionType = new TypeToken<ArrayList<Emotion>>() {
            }.getType();
            emotions_2 = gson.fromJson(reader, listEmotionType);


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void setText_bar() {
        Emotion emotion_1 = adapter_2.getItem(position);
        String message_1 = emotion_1.getMessage();
        Date date_1 = emotion_1.getDate();
        String emotion_txt = emotion_1.getEmotion();

        int Year = date_1.getYear() + 1900;
        int Month = date_1.getMonth() + 1;
        int Day = date_1.getDay();
        int Hour = date_1.getHours();
        int Minute = date_1.getMinutes();
        int Second = date_1.getSeconds();
        change_date.setText(Year + "/" + Month + "/" + Day);
        change_time.setText(Hour + ":" + Minute + ":" + Second);
        message = (EditText) findViewById(R.id.message);
        emotion = (TextView) findViewById(R.id.emotion);
        emotion.setText(emotion_txt);
        message.setText(message_1);

    }

    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(ThirdActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                change_date.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void showTimePickerDialog() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(ThirdActivity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {

                change_time.setText(hour + ":" + minute + ":" + "00");
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();

    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emotions_2, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}