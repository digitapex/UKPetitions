package com.spitslide.ukpetitions;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView.LayoutManager layoutManager;
    private String searchQuery = "";
    private boolean isSearchActivity;
    private String state;

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
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        } else {
            actionbar.setTitle(searchQuery);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();

                        state = getResources().getResourceEntryName(menuItem.getItemId());
                        Bundle bundle = new Bundle();
                        bundle.putString("state", state);
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
        PetitionsFragment petitionsFragment = new PetitionsFragment();
        petitionsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, petitionsFragment)
                .commit();

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
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchItem.collapseActionView();
                    Bundle appData = new Bundle();
                    // passing currently selected item in drawer to search only within that context
                    appData.putString("state", state);
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
