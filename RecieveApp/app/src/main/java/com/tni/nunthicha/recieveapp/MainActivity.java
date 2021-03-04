package com.tni.nunthicha.recieveapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private severconnect connect = new severconnect();
    private jsonClass jsondata = new jsonClass();

    private  final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    JSONObject jsonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        severconnect connect = new severconnect();
        String data;
        jsonOrder = null;

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button = findViewById(R.id.rec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String data1;
                    data1 = connect.getData("test");
                    jsonOrder = new JSONObject(data1);
                    System.out.print("Json output : ");
                    System.out.println(jsonOrder.toString());
                } catch (InterruptedException | JSONException e) {
                    e.printStackTrace();
                }

                Iterator<String> iter = jsonOrder.keys();
                mWordList.clear();
                while (iter.hasNext()) {
                    String key = iter.next();
                    mWordList.addLast(key);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        try {
            data = connect.getData("test");
            jsonOrder = new JSONObject(data);
            System.out.print("Json output : ");
            System.out.println(jsonOrder.toString());
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        Iterator<String> iter = jsonOrder.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            mWordList.addLast(key);
        }

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWordList.clear();
                mAdapter.notifyDataSetChanged();

            }
        });

    }

    public void startMenulistIntent(String key){
        Intent intent = new Intent(this, Menulist.class);
        intent.putExtra("EXTRAKEY", key);
        intent.putExtra("EXTRAJSON", jsonOrder.toString());
        startActivity(intent);
    }
}