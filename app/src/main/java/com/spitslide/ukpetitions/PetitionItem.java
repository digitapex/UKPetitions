package com.spitslide.ukpetitions;


import java.io.Serializable;

public class PetitionItem implements Serializable {

    private String title;
    private int signatureCount;

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
}
