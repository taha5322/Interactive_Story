package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Choice;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;

    private Stack<Integer> stack = new Stack<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView)findViewById(R.id.storyImage);
        storyTextView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);



        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.d(StoryActivity.class.toString(), name);

        story = new Story();
        //0 is the default page
        loadPages(0);
    }

    private void loadPages(int i) {
        stack.push(i);

        //gets the newest page
        final Page page = story.getPage(i);

        //in order to get a drawable image, you use the bottom function
        //in order to set an image to a picture, you have to get it using the
        //ConectCompat.getDrawable(this, id of view)
        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        //add name if placeholder is included
        pageText = String.format(pageText, name);

        storyTextView.setText(pageText);

        if (page.isFinalPage()) {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText("Play Again");

            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPages(0);
                    //adding "finish()" would finish this acitivty and tske u
                    //back to main activity.
                }
            });

        } else {
            loadPage(page);

        }
    }

    private void loadPage(final Page page) {


        //makes sure the buttons are still visible if the user plays again
        //as on the last acitivty, we make the first button invisible.
        choice1Button.setVisibility(View.VISIBLE);
        choice2Button.setVisibility(View.VISIBLE);

        //We made button class that accepts a text id
        //and then we initiaised all those buttons in the Story() class with text id's on the choice
        //Over here we are taking the id that was set in the storyClass() and setting it to the
        //choice1 button through the setText() which supposedly takes an id.
        choice1Button.setText(page.getChoice1().getTextId());

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPages(nextPage);
            }
        });

        choice2Button.setText(page.getChoise2().getTextId());

        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoise2().getNextPage();
                loadPages(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        stack.pop();
        if(stack.isEmpty()){
            super.onBackPressed();
        } else{
            //this is called twice so that the previous acitivity
            //which you go back to isn't added in the stack twice.
            loadPages(stack.pop());
        }
    }
}
