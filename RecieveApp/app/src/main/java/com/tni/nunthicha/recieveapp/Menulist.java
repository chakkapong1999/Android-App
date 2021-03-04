package com.tni.nunthicha.recieveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Menulist extends AppCompatActivity {

    JSONObject jsonData;
    String key;
    String menu;
    String json;
    JSONObject table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);

        Intent intent = getIntent();

        try {
            json = intent.getStringExtra("EXTRAJSON");
            System.out.println(json);
            jsonData = new JSONObject(json);
            key = intent.getStringExtra("EXTRAKEY");
            System.out.println(key);
            table = jsonData.getJSONObject(key);
            System.out.println(table.toString());
            menu =  table.getString("1") + "\n\n" +
                    table.getString("2") + "\n\n" +
                    table.getString("3") + "\n\n" +
                    table.getString("4") + "\n\n" +
                    "  Total: " + table.getString("Total") + "\n\n" +
                    "  phone number: " + table.getString("phone number") + "\n\n" +
                    "  note: " + table.getString("note");

            TextView view = findViewById(R.id.Menulist);
            view.setText(menu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onclick_done(View view) throws JSONException {
        finish();
    }
}