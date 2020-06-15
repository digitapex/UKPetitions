package com.spitslide.ukpetitions;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView.LayoutManager layoutManager;
    private String searchQuery = "";
    private boolean isSearchActivity;
    private String state;
    private String archived = "";
    private Consent consent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);

            // for extra data passed to search activity
            Bundle appData = intent.getBundleExtra(SearchManager.APP_DATA);
            if (appData != null) {
                state = appData.getString("state");
                archived = appData.getString("archived");
            }
            isSearchActivity = true;
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        if (!isSearchActivity) {
            // if search activity, we just want the default arrow icon, and not this hamburger
            actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        } else {
            actionbar.setTitle(searchQuery);
        }

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.all).setChecked(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        state = getResources().getResourceEntryName(menuItem.getItemId());
                        if (menuItem.getGroupId() == R.id.archived) {
                            // menu items can't have the same id and can't have tags, but our items use the same query parameters in archived context, so we just remove last dummy character
                            state = state.substring(0, state.length() - 1);
                            archived = "archived";
                        } else {
                            archived = "";
                        }

                        // checkableBehavior="single" in xml apparently doesn't work across multiple groups, so we first remove selection on "all" in case it's last selected (because we selected it on start)
                        navigationView.getMenu().findItem(R.id.all).setChecked(false);
                        navigationView.setCheckedItem(menuItem.getItemId());
                        drawerLayout.closeDrawers();

                        Bundle bundle = new Bundle();
                        bundle.putString("state", state);
                        bundle.putString("archived", archived);
                        PetitionsFragment petitionsFragment = new PetitionsFragment();
                        petitionsFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, petitionsFragment)
                                .commit();
                        return true;
                    }
                });

        Bundle bundle = new Bundle();
        bundle.putString("searchQuery", searchQuery);
        bundle.putString("state", state);
        bundle.putString("archived", archived);
        PetitionsFragment petitionsFragment = new PetitionsFragment();
        petitionsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, petitionsFragment)
                .commit();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        consent = new Consent(this);
        consent.checkForConsentAndDisplayAds(false);
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (consent.shouldAddConsentToMenu) {
            menu.findItem(R.id.consent).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isSearchActivity) {
            // don't add search icon if it's search activity
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint("Search here...");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchItem.collapseActionView();
                    Bundle appData = new Bundle();
                    // passing currently selected item in drawer to search only within that context
                    appData.putString("state", state);
                    appData.putString("archived", archived);
                    // https://stackoverflow.com/a/39082195/9702500
                    searchView.setAppSearchData(appData);
                    // return false to perform default search behavior (new activity), true would mean that we handle the search ourselves
                    return false;
                }

                @Override
                public boolean onQueryTextChange(final String s) {
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!isSearchActivity) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    finish();
                }
                return true;
            case R.id.share:
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.recommend_app));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.recommend_text));
                startActivity(Intent.createChooser(shareIntent, getString(R.string.recommend_title)));
                break;
            case R.id.rate:
                Uri uriMarket = Uri.parse(getString(R.string.rate_app_market));
                Uri uriWeb = Uri.parse(getString(R.string.rate_app_web));
                Intent rateIntentMarket = new Intent(Intent.ACTION_VIEW, uriMarket);
                Intent rateIntentWeb = new Intent(Intent.ACTION_VIEW, uriWeb);
                try {
                    startActivity(rateIntentMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(rateIntentWeb);
                }
                break;
            case R.id.consent:
                consent = new Consent(MainActivity.this);
                consent.checkForConsentAndDisplayAds(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
