package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets the text in the name dialogue box
        nameField = (EditText)findViewById(R.id.nameEditText);

        startButton = (Button)findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets the text entered into the name fiels and converts it from "EditText" to a string.
                String name = nameField.getText().toString();
                startStory(name);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    private void startStory(String name){
        //intent is called when you want to hand the current piece of code to something else
        //eg: go to a new activity. It required the current class as context
        //and then the new activity followed by .class which is the acitivty it will switch to
        Intent intent = new Intent(this, StoryActivity.class);

        //makes a new instance of all the shit in our values folder which we can refer to
        //Kind of like making a number constant object and using that to refer to the name
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);

        //sends data onto the next activity through a key. The "name" is used to label the piece of data.
        intent.putExtra(key, name);

        //this takes that intent and puts it into play
        startActivity(intent);
    }


}
