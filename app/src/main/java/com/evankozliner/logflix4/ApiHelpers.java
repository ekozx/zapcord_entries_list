package com.evankozliner.logflix4;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

/**
 * Created by maratkozliner on 5/11/15.
 */
public class ApiHelpers {

    public void requestEntries(Context c) {
        RequestQueue queue = Volley.newRequestQueue(c);

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
        }
    }
}
