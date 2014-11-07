package com.ru.appdev.TabReader;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;


public class TabContentFragment extends Fragment {
    private String currentDocument = ""; // "https://dl.dropboxusercontent.com/u/9318533/Combat.pdf"; // Placeholder test document

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //View v = inflater.inflate(R.layout.action_bar_tab_content, container, false);

        final EditText txtUrl = new EditText(this.getActivity());

        // Set the default text to a link of the Queen

        new AlertDialog.Builder(this.getActivity())
        .setTitle(R.string.file_dialog_title)
        .setMessage(R.string.file_dialog_text)
        .setView(txtUrl)
        .setPositiveButton(R.string.file_dialog_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                currentDocument = txtUrl.getText().toString();
            }
        })
        .setNegativeButton(R.string.file_dialog_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        })
        .show();
        if(currentDocument != null) {
            WebView wv = new WebView(this.getActivity());
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            wv.getSettings().setJavaScriptEnabled(true);
            System.out.println("Attempting to render https://docs.google.com/gview?embedded=true&url="+currentDocument);  // DEBUG
            wv.loadUrl("https://docs.google.com/gview?embedded=true&url=" + currentDocument);
            return wv;
        }
        return null;
    }


}