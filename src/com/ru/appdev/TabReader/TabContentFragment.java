package com.ru.appdev.TabReader;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;


public class TabContentFragment extends Fragment {
    private String currentDocument = ""; // "https://dl.dropboxusercontent.com/u/9318533/Combat.pdf"; // Placeholder test document
    WebView wv;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Dialog code based on http://twigstechtips.blogspot.com/2011/10/android-allow-user-to-editinput-text.html
        // Some changes mainly to work around the asynchronous nature of AlertDialog.
        final EditText txtUrl = new EditText(this.getActivity());
        wv = new WebView(this.getActivity());

        AlertDialog dialog = new AlertDialog.Builder(this.getActivity())
                .setTitle(R.string.file_dialog_title)
                .setMessage(R.string.file_dialog_text)
                .setView(txtUrl)
                .setPositiveButton(R.string.file_dialog_positive, new DialogInterface.OnClickListener() {

                    // This method of using WebView and Google Docs to render a PDF based on
                    // stackoverflow.com/questions/2456344/display-pdf-within-app-on-android
                    // answer by user "Agarwal Shankar".
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentDocument = txtUrl.getText().toString();
                        wv.loadUrl("https://docs.google.com/gview?embedded=true&url=" + currentDocument);
                        String[] splitUrl = currentDocument.split("/");
                        String fileName = splitUrl[splitUrl.length-1];
                        ActionBar bar = getActivity().getActionBar();
                        bar.getSelectedTab().setText(fileName);
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.file_dialog_negative, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).create();
        if(currentDocument.equals("")){
            dialog.show();
        }

        if(currentDocument != null) {
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.loadUrl("https://docs.google.com/gview?embedded=true&url=" + currentDocument);
            return wv;
        }
        return null;
    }


}