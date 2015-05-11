package com.evankozliner.logflix4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

    public ApiAdaptor(JSONArray jsonEntries) {
        if(jsonEntries != null) {
            System.out.println("Json entries");
            this.entries = jsonEntries;
        } else {
            System.out.println("No Json entries");
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
//        entries = ApiHelpers.apiEntries;
        ViewHolder holder;
        View entryTemplate = convertView;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            entryTemplate = inflater.inflate(R.layout.entry_template, null, true);
            holder = new ViewHolder();
            holder.pictureUrl = (TextView) entryTemplate.findViewById(R.id.pictureUrl);
            holder.title = (TextView) entryTemplate.findViewById(R.id.title);
            //settag method is for to associate this data to android
            entryTemplate.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.pictureUrl.setText(entries.getJSONObject(position).getString("thumbnail_file_name"));
            holder.title.setText(entries.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entryTemplate;
    }

    static class ViewHolder {
        public TextView pictureUrl;
        public TextView title;
    }
}
