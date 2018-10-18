package com.spitslide.ukpetitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class PetitionItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petitions_item);
        PetitionItem petitionItem = (PetitionItem)getIntent().getSerializableExtra("PetitionItem");
        fillViewsWithData(petitionItem);
        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    private void fillViewsWithData(PetitionItem petitionItem) {
        TextView title = findViewById(R.id.petition_title);
        TextView background = findViewById(R.id.petition_background);
        TextView signatures = findViewById(R.id.petition_signatures);
        TextView govResponseSummary = findViewById(R.id.gov_response_summary);
        TextView govResponseDetails = findViewById(R.id.gov_response_details);
        TextView parliamentTranscript = findViewById(R.id.parliament_transcript);
        TextView parliamentVideo = findViewById(R.id.parliament_video);
        title.setText(petitionItem.getTitle());
        background.setText(petitionItem.getBackground());
        String sigWithSeparator = String.format("%,d", petitionItem.getSignatureCount());
        signatures.setText(sigWithSeparator + " signatures, " + petitionItem.getState());
        if (petitionItem.getGovResponseSummary() != null) {
            govResponseSummary.setText(petitionItem.getGovResponseSummary());
            govResponseDetails.setText(petitionItem.getGovResponseDetails());
        } else {
            govResponseSummary.setText(R.string.not_available);
        }
        if (petitionItem.getParlDebateTranscript() == null || petitionItem.getParlDebateTranscript().equals("")) {
            parliamentTranscript.setText(R.string.not_available);
        } else {
            parliamentTranscript.setText("Transcript:\r\n" + petitionItem.getParlDebateTranscript());
            parliamentVideo.setText("Video:\r\n" + petitionItem.getParlDebateVideo());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
