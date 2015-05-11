package com.evankozliner.logflix4;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.LinkedList;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by maratkozliner on 5/10/15.
 */
public class ApiAdaptor extends BaseAdapter {

    static List<String> thumbnailUriList = new LinkedList<String>();
    static List<String> titleList = new LinkedList<String>();

    public ApiAdaptor(Context c) {
//        HttpAsyncTask async = new HttpAsyncTask();
//        async.execute("www.zapcord.com/api/v1/entries.json");
        System.out.println("Hi there");
        requestEntries(c);
    }

    private void requestEntries(Context c) {
        RequestQueue queue = Volley.newRequestQueue(c);

        String url = "http://www.zapcord.com/api/v1/entries.json";
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("received response");
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsObjRequest);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
