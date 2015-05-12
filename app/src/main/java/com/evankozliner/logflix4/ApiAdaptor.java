package com.evankozliner.logflix4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static JSONArray entries;
    ViewHolder holder;

    /**
     * Generic constructor.
     * @param jsonEntries
     *        The JSON recieved from the Zapcord API. This should be a JSON array
     *        populated with entries.
     */
    public ApiAdaptor(JSONArray jsonEntries) {
        if(jsonEntries != null) {
            this.entries = jsonEntries;
        } else {
            this.entries = new JSONArray();
        }
    }

    @Override
    public int getCount() {
        return this.entries.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return this.entries.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View entryTemplate = convertView;
        ImageView imageView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            entryTemplate = inflater.inflate(R.layout.entry_template, null, true);
            holder = new ViewHolder();
            holder.pictureUrl = (ImageView) entryTemplate.findViewById(R.id.pictureUrl);
            holder.title = (TextView) entryTemplate.findViewById(R.id.title);
            //settag method is for to associate this data to android
            entryTemplate.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            JSONObject entry=  entries.getJSONObject(position);
//            holder.pictureUrl.setText(entry.getString("thumbnail_file_name"));
            holder.title.setText(entry.getString("title"));
            String thumbnail_url = entry.getString("thumbnail_file_name");
            if (thumbnail_url.isEmpty()) {
                holder.pictureUrl.setImageResource(R.drawable.missing);
            } else {
                new RetrieveImageTask().execute(thumbnail_url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entryTemplate;
    }

    class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            System.out.println("Fetching image...");
            System.out.println(params[0]);
            return getImageBitMap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bm) {
            holder.pictureUrl.setImageBitmap(bm);
        }
    }

    /**
     * Returns a bitmap from the url string from Rotten Tomatoes to be populate
     * the entryTemplate imageView, picture.
     * @param thumbnailUrl
     *        The Rotten Tomatoes thumbnail url returned by the API call to Zapcord.
     * @return
     *        The bitmap generate from the thumbnail url.
     */
    private Bitmap getImageBitMap(String thumbnailUrl) {
        try {
            URL url = new URL(thumbnailUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            System.out.println("Returning the bitmap...");
            return myBitmap;
        }
        catch (IOException e) {
            e.printStackTrace();
            /* Return a generic image here instead */
            return null;
        }
    }

    /**
     * View Holder for individual entries in the listView.
     */
    static class ViewHolder {
        public ImageView pictureUrl;
        public TextView title;
    }
}
