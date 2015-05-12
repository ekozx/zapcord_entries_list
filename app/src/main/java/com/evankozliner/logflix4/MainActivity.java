package com.evankozliner.logflix4;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ListActivity {
    public BaseAdapter apiAdaptor;
    TextView connected;
    public Button buttonConnect;
    Context listActivityContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if(isConnected()) {
            buttonConnect = (Button) findViewById(R.id.button_connect);
            buttonConnect.setOnClickListener(new connectListener());
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
        if (networkInfo != null && networkInfo.isConnected()) {
            connected = (TextView) findViewById(R.id.connectedTextView);
            connected.setText("Connected :)");

            return true;
        } else {
            connected = (TextView) findViewById(R.id.connectedTextView);
            connected.setText("Not connected ):");

            return false;
        }
    }

    /**
     * Listener for clicking the connect button.
     */
    private class connectListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(isConnected()) {
                RequestQueue queue = Volley.newRequestQueue(listActivityContext);

                String url = "http://www.zapcord.com/api/v1/entries.json";
                JsonArrayRequest jsObjRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new ApiListener(),
                        new ApiErrListener()
                );

                queue.add(jsObjRequest);
            }
        }
    }
    private class ApiErrListener implements  Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            volleyError.printStackTrace();
        }
    }
    private class ApiListener implements Response.Listener<JSONArray> {
        @Override
        public void onResponse(JSONArray jsonArray) {
            System.out.println(jsonArray);
            System.out.println("received response");
            ApiAdaptor apiAdaptor = new ApiAdaptor(jsonArray);
            setListAdapter(apiAdaptor);
        }
    }
}
