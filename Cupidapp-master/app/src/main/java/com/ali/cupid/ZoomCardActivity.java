package com.ali.cupid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ali.cupid.Cards.cardObject;

public class ZoomCardActivity extends AppCompatActivity {

    private TextView    mName,
                        mJob,
                        mAbout;

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_card);

        Intent i = getIntent();
        cardObject mCardObject = (cardObject)i.getSerializableExtra("cardObject");

        mName = findViewById(R.id.name);
        mJob = findViewById(R.id.job);
        mAbout = findViewById(R.id.about);
        mImage = findViewById(R.id.image);

        mName.setText(mCardObject.getName() + ", " + mCardObject.getAge());
        mJob.setText(mCardObject.getJob());
        mAbout.setText(mCardObject.getAbout());

        if(!mCardObject.getProfileImageUrl().equals("default"))
            Glide.with(getApplicationContext()).load(mCardObject.getProfileImageUrl()).into(mImage);
    }



}
