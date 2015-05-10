package com.evankozliner.logflix4;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.ListActivity;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;


public class MyActivity extends ListActivity {
    public BaseAdapter apiAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        apiAdaptor = new ApiAdaptor();
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
