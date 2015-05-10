package com.evankozliner.logflix4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maratkozliner on 5/10/15.
 */
public class ApiAdaptor extends BaseAdapter {

    static List<String> thumbnailUriList = new LinkedList<String>();
    static List<String> titleList = new LinkedList<String>();

    public ApiAdaptor() {
        HttpAsyncTask async = new HttpAsyncTask();
        async.execute("www.zapcord.com/api/v1/entries.json");
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
