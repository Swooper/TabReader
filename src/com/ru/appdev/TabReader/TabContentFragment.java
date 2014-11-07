package com.ru.appdev.TabReader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class TabContentFragment extends Fragment {
    private String currentDocument = "https://dl.dropboxusercontent.com/u/9318533/Combat.pdf"; // Placeholder test document

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.action_bar_tab_content, container, false);
        if(currentDocument != null) {
            WebView wv = new WebView(this.getActivity());
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("https://docs.google.com/gview?embedded=true&url=" + currentDocument);
            return wv;
        }
        return v;
    }


}