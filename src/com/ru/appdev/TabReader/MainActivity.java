package com.ru.appdev.TabReader;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_bar_tabs);
        onToggleTabs(); // To display the tab bar when the app is started. May need revising later.
    }

    public void onAddTab() {
        try {
            final ActionBar bar = getActionBar();
            //final int tabCount = bar.getTabCount();
            //final String text = "text";
            bar.addTab(bar.newTab()
                .setText(R.string.new_tab)
                .setTabListener(new TabListener(new TabContentFragment())));
        }
        catch (NullPointerException npe) {
            System.out.println("ERROR in onAddTab: " + npe);
        }
    }

    public void onRemoveTab() {
        final ActionBar bar = getActionBar();
        try {
            if (bar.getTabCount() > 0)
                bar.removeTabAt(bar.getSelectedTab().getPosition());
        }
        catch (NullPointerException npe) {
            System.out.println("ERROR in onRemoveTab: " + npe);
        }
    }

    public void onToggleTabs() {
        final ActionBar bar = getActionBar();
        try {
            if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
                bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
            } else {
                bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
            }
        }
        catch (NullPointerException npe) {
            System.out.println("ERROR in onToggleTabs: " + npe);
        }
    }

    // Might want this method later, keeping it commented out for now.
    /*public void onRemoveAllTabs(View v) {
        try {
            getActionBar().removeAllTabs();
        }
        catch (NullPointerException npe) {
            System.out.println("ERROR in onRemoveAllTabs: " + npe);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                onAddTab();
                return true;
            case R.id.action_open:
                // TODO Handle file opening here
                return true;
            case R.id.action_close:
                onRemoveTab();
                return true;
            case R.id.action_search:
                // TODO Handle search here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * A TabListener receives event callbacks from the action bar as tabs
     * are deselected, selected, and reselected.
     **/
    private class TabListener implements ActionBar.TabListener {
        private TabContentFragment mFragment;

        public TabListener(TabContentFragment fragment) {
            mFragment = fragment;
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            ft.add(R.id.webView, mFragment);
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(mFragment);
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            //Toast.makeText(MainActivity.this, "Reselected!", Toast.LENGTH_SHORT).show();
        }
    }

}
