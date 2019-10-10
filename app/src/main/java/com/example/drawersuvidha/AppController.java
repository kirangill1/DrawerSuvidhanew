package com.example.drawersuvidha;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

class AppController {
    private RequestQueue mRequestQueue;
    private Activity c;


    @SuppressLint("StaticFieldLeak")
    private static AppController mInstance;

    AppController(Activity c)
    {
        mInstance = this;
        this.c = c;

    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(c);
        }

        return mRequestQueue;
    }

    void addToRequestQueue(JsonObjectRequest jobjreq) {
        getRequestQueue().add(jobjreq);
    }}




