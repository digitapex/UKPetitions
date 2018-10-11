
package com.spitslide.ukpetitions.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GovernmentResponse {

    @SerializedName("responded_on")
    @Expose
    private String respondedOn;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getRespondedOn() {
        return respondedOn;
    }

    public void setRespondedOn(String respondedOn) {
        this.respondedOn = respondedOn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
