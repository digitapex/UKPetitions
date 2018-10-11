
package com.spitslide.ukpetitions.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("additional_details")
    @Expose
    private String additionalDetails;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("signature_count")
    @Expose
    private Integer signatureCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("rejected_at")
    @Expose
    private Object rejectedAt;
    @SerializedName("opened_at")
    @Expose
    private String openedAt;
    @SerializedName("closed_at")
    @Expose
    private String closedAt;
    @SerializedName("moderation_threshold_reached_at")
    @Expose
    private String moderationThresholdReachedAt;
    @SerializedName("response_threshold_reached_at")
    @Expose
    private String responseThresholdReachedAt;
    @SerializedName("government_response_at")
    @Expose
    private String governmentResponseAt;
    @SerializedName("debate_threshold_reached_at")
    @Expose
    private String debateThresholdReachedAt;
    @SerializedName("scheduled_debate_date")
    @Expose
    private String scheduledDebateDate;
    @SerializedName("debate_outcome_at")
    @Expose
    private String debateOutcomeAt;
    @SerializedName("creator_name")
    @Expose
    private Object creatorName;
    @SerializedName("rejection")
    @Expose
    private Object rejection;
    @SerializedName("government_response")
    @Expose
    private GovernmentResponse governmentResponse;
    @SerializedName("debate")
    @Expose
    private Debate debate;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public Integer getSignatureCount() {
        return signatureCount;
    }

    public void setSignatureCount(Integer signatureCount) {
        this.signatureCount = signatureCount;
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

    public Object getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(Object rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public String getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getModerationThresholdReachedAt() {
        return moderationThresholdReachedAt;
    }

    public void setModerationThresholdReachedAt(String moderationThresholdReachedAt) {
        this.moderationThresholdReachedAt = moderationThresholdReachedAt;
    }

    public String getResponseThresholdReachedAt() {
        return responseThresholdReachedAt;
    }

    public void setResponseThresholdReachedAt(String responseThresholdReachedAt) {
        this.responseThresholdReachedAt = responseThresholdReachedAt;
    }

    public String getGovernmentResponseAt() {
        return governmentResponseAt;
    }

    public void setGovernmentResponseAt(String governmentResponseAt) {
        this.governmentResponseAt = governmentResponseAt;
    }

    public String getDebateThresholdReachedAt() {
        return debateThresholdReachedAt;
    }

    public void setDebateThresholdReachedAt(String debateThresholdReachedAt) {
        this.debateThresholdReachedAt = debateThresholdReachedAt;
    }

    public String getScheduledDebateDate() {
        return scheduledDebateDate;
    }

    public void setScheduledDebateDate(String scheduledDebateDate) {
        this.scheduledDebateDate = scheduledDebateDate;
    }

    public String getDebateOutcomeAt() {
        return debateOutcomeAt;
    }

    public void setDebateOutcomeAt(String debateOutcomeAt) {
        this.debateOutcomeAt = debateOutcomeAt;
    }

    public Object getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(Object creatorName) {
        this.creatorName = creatorName;
    }

    public Object getRejection() {
        return rejection;
    }

    public void setRejection(Object rejection) {
        this.rejection = rejection;
    }

    public GovernmentResponse getGovernmentResponse() {
        return governmentResponse;
    }

    public void setGovernmentResponse(GovernmentResponse governmentResponse) {
        this.governmentResponse = governmentResponse;
    }

    public Debate getDebate() {
        return debate;
    }

    public void setDebate(Debate debate) {
        this.debate = debate;
    }

}
