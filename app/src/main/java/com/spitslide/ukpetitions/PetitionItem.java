package com.spitslide.ukpetitions;


import java.io.Serializable;

public class PetitionItem implements Serializable {

    private String title;
    private int signatureCount;
    private String background;
    private String additionalDetails;
    private String state;
    private String govResponseSummary;
    private String govResponseDetails;
    private String govDebateTranscript;
    private String govDebateVideo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSignatureCount() {
        return signatureCount;
    }

    public void setSignatureCount(int signatureCount) {
        this.signatureCount = signatureCount;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGovResponseSummary() {
        return govResponseSummary;
    }

    public void setGovResponseSummary(String govResponseSummary) {
        this.govResponseSummary = govResponseSummary;
    }

    public String getGovResponseDetails() {
        return govResponseDetails;
    }

    public void setGovResponseDetails(String govResponseDetails) {
        this.govResponseDetails = govResponseDetails;
    }

    public String getGovDebateTranscript() {
        return govDebateTranscript;
    }

    public void setGovDebateTranscript(String govDebateTranscript) {
        this.govDebateTranscript = govDebateTranscript;
    }

    public String getGovDebateVideo() {
        return govDebateVideo;
    }

    public void setGovDebateVideo(String govDebateVideo) {
        this.govDebateVideo = govDebateVideo;
    }

}
