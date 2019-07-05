package com.divineventures.worldcountries;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<MyData> myData;
    private String url = "https://restcountries.eu/rest/v2/all";
    RvAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerView);
        rv.hasFixedSize();
        DividerItemDecoration hdecor = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        DividerItemDecoration vdecor = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(vdecor);
        rv.addItemDecoration(hdecor);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(layoutManager);
        myData =  new ArrayList<>();
        adapter = new RvAdapter(this,myData);
        rv.setAdapter(adapter);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        MyTask task = new MyTask();
        task.execute();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    JSONArray jsonArray = new JSONArray(response.body().string());

                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);

                        Log.v("JSON RESPONSE",object.getString("name"));

                        String name = object.getString("name");
                        String region = object.getString("region");
                        String flag_url = object.getString("flag");
                        String population = object.getString("population");

                        MyData data = new MyData(name,flag_url,population,region);
                        myData.add(data);


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           adapter.notifyDataSetChanged();
        }
    }
}
