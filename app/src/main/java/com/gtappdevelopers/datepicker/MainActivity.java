package com.gtappdevelopers.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4;
    ToggleButton toggleButton;
    Button button, button1, apply,button4;
    RadioButton radioButton;
    RadioGroup radioGroup;
AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        radioGroup = findViewById(R.id.radiogroup);
        button = findViewById(R.id.button);
        button4 = findViewById(R.id.button4);

        apply = findViewById(R.id.button3);
        toggleButton = findViewById(R.id.toggleButton);


button4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showalerdaialouge();


    }
});
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtimepoicker();
            }
        });
        button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked()) {
                    textView3.setText("on");
                } else {
                    textView3.setText("off");
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioid);
                    textView4.setText("yourchoice :" + radioButton.getText());

                } catch (Exception e) {
                    e.getMessage();
                    Toast.makeText(MainActivity.this, "Nothing selected please select something ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void showtimepoicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selectedtime = hourOfDay + ":" + minute;
                textView.setText(selectedtime);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int mint = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, mint, true);
        timePickerDialog.show();
    }

    void datepicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selecteddate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textView2.setText(selecteddate);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, dayofmonth);
        datePickerDialog.show();
    }
    void showalerdaialouge(){
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage("are you sure to go second activity");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "you clicked no ", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
