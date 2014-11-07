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

    //Tab functionality based on http://www.coderzheaven.com/2012/10/08/dynamically-adding-removing-toggling-removing-actionbar-tabs-android-part-2/
    public void onAddTab() {
        try {
            final ActionBar bar = getActionBar();
            bar.addTab(bar.newTab()
                .setText(R.string.new_tab)
                .setTabListener(new TabListener(new TabContentFragment())));
        }
        catch (NullPointerException npe) {
            System.out.println("ERROR in onAddTab: " + npe);
        }
    }

    // Close a tab and dispose of it properly.
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

    // Toggle the tab bar on/off
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

    // Inflate the menu items for use in the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle presses on the action bar items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                onAddTab();
                return true;
            case R.id.action_close:
                onRemoveTab();
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
            // This method must be here, but we don't want to do anything special when it's called
        }
    }

}
