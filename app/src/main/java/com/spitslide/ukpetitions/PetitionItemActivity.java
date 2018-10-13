package com.spitslide.ukpetitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;


public class PetitionItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petitions_item);
        PetitionItem petitionItem = (PetitionItem)getIntent().getSerializableExtra("PetitionItem");
        ((TextView)findViewById(R.id.petition_title)).setText(petitionItem.getTitle());
    }
}
