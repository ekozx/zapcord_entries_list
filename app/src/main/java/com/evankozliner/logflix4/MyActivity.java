package com.evankozliner.logflix4;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.ListActivity;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class MyActivity extends ListActivity {
    public BaseAdapter apiAdaptor;
    TextView connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if(isConnected()) {
            apiAdaptor = new ApiAdaptor(this);
            connected = (TextView) findViewById(R.id.connectedTextView);
            connected.setText("Connected :)");
        }
        else {
            connected = (TextView) findViewById(R.id.connectedTextView);
            connected.setText("Not connected ):");
        }
    }

    /**
     * A getter for connectivity.
     * @return
     *        A boolean checking if the device is connected.
     */
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE); ;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
