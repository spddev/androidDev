package com.example.drawshapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView myView = findViewById(R.id.myView);
        Button undoBtn = findViewById(R.id.btnUndo);
        RadioGroup figuresRadioGroup = findViewById(R.id.FiguresRadioGroup);
        RadioGroup moveRadioGroup = findViewById(R.id.MoveRadioGroup);
        Spinner spinner = findViewById(R.id.spinner);



        class Listener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

            @Override
            public void onClick(View v) {
                myView.undo();
            }

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioRect: myView.setTypeShape(MyView.TYPE_RECT); break;
                    case R.id.radioCircle: myView.setTypeShape(MyView.TYPE_CIRCLE); break;
                    case R.id.radioTriangle: myView.setTypeShape(MyView.TYPE_TRIANGLE); break;
                    case R.id.radioDraw: myView.setMode(MyView.MODE_DRAW); break;
                    case R.id.radioMove: myView.setMode(MyView.MODE_MOVE); break;

                }
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String [] colors = getResources().getStringArray(R.array.Colors);
                myView.setColor(colors[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }

        undoBtn.setOnClickListener(new Listener());
        figuresRadioGroup.setOnCheckedChangeListener(new Listener());
        moveRadioGroup.setOnCheckedChangeListener(new Listener());
        spinner.setOnItemSelectedListener(new Listener());
    }



}