package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    TextView t1,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setupUIviews();
        String des="THIS IS AN APP OF SUDOKU \n THIS APP HAVE FEATURE OF PLAYING DIFFERENT TYPE OF SUDOKU AND IT ALSO HAVE A FEATURE TO CHECK WHETHER YOU SOLVED A SUDOKU CORRECTLY OR NOT";
        t1.setText(des);

        link.setMovementMethod(LinkMovementMethod.getInstance());

        link.setLinkTextColor(Color.BLUE);
    }

    private void setupUIviews()
    {
        t1=findViewById(R.id.textView19);
        t1.setMovementMethod(new ScrollingMovementMethod());
        link=findViewById(R.id.textView23);
    }
}