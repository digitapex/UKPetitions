package com.spitslide.ukpetitions;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.net.MalformedURLException;
import java.net.URL;

public class Consent {

    private Context context;
    private ConsentForm form;
    public boolean shouldAddConsentToMenu;
    private String privacyPolicy = "https://gist.githubusercontent.com/digitapex/5dfadd2e7522660033a3d37b0d5dee8b/raw/73e38a6116f98b3e804270c7eef447a60ad369f3/UKPetitionsPrivacyPolicy";

    public Consent(Context context) {
        this.context = context;
    }

    void checkForConsentAndDisplayAds(boolean isUserChangingConsent) {

        // uncomment for testing purposes
//        ConsentInformation.getInstance(context).
//                setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_NOT_EEA);
//        ConsentInformation.getInstance(context).
//                setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);

        if (isUserChangingConsent) {
            ConsentInformation.getInstance(context).setConsentStatus(ConsentStatus.UNKNOWN);
        }

        ConsentInformation consentInformation = ConsentInformation.getInstance(context);
        String[] publisherIds = { BuildConfig.ADMOB_PUBLISHER_ID };
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                switch (consentStatus) {
                    case PERSONALIZED:
                        shouldAddConsentToMenu = true;
                        showPersonalizedAds();
                        break;
                    case NON_PERSONALIZED:
                        shouldAddConsentToMenu = true;
                        showNonPersonalizedAds();
                        break;
                    case UNKNOWN:
                        if (ConsentInformation.getInstance(context)
                                .isRequestLocationInEeaOrUnknown()) {
                            requestConsent();
                        } else {
                            showPersonalizedAds();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
            }
        });
    }

    private void requestConsent() {
        URL privacyUrl = null;
        try {
            privacyUrl = new URL(privacyPolicy);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        form = new ConsentForm.Builder(context, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        showForm();
                    }

                    @Override
                    public void onConsentFormOpened() {
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        switch (consentStatus) {
                            case PERSONALIZED:
                                shouldAddConsentToMenu = true;
                                showPersonalizedAds();
                                break;
                            case NON_PERSONALIZED:
                                shouldAddConsentToMenu = true;
                                showNonPersonalizedAds();
                                break;
                            case UNKNOWN:
                                showNonPersonalizedAds();
                                break;
                        }
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .build();
        form.load();
    }

    private void showPersonalizedAds() {
        AdView adView = ((Activity) context).findViewById(R.id.adview1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void showNonPersonalizedAds() {
        Bundle extras = new Bundle();
        extras.putString("npa", "1");
        AdView adView = ((Activity) context).findViewById(R.id.adview1);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        adView.loadAd(adRequest);
    }

    private void showForm() {
        form.show();
    }
}