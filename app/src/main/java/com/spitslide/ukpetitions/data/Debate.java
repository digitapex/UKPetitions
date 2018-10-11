
package com.spitslide.ukpetitions.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Debate {

    @SerializedName("debated_on")
    @Expose
    private String debatedOn;
    @SerializedName("transcript_url")
    @Expose
    private String transcriptUrl;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("debate_pack_url")
    @Expose
    private String debatePackUrl;
    @SerializedName("overview")
    @Expose
    private String overview;

    public String getDebatedOn() {
        return debatedOn;
    }

    public void setDebatedOn(String debatedOn) {
        this.debatedOn = debatedOn;
    }

    public String getTranscriptUrl() {
        return transcriptUrl;
    }

    public void setTranscriptUrl(String transcriptUrl) {
        this.transcriptUrl = transcriptUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDebatePackUrl() {
        return debatePackUrl;
    }

    public void setDebatePackUrl(String debatePackUrl) {
        this.debatePackUrl = debatePackUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
