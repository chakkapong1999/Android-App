package com.tni.nunthicha.foodorder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tni.nunthicha.foodorder.extra.MESSAGE";


    public String mOrderList[] = new String[4];
    private int count = 0;
    private int price = 0;

    CheckBox kapao,rice,egg,padthai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kapao = (CheckBox) findViewById(R.id.krapao_box);
        egg = (CheckBox) findViewById(R.id.egg_box);
        rice = (CheckBox) findViewById(R.id.rice_box);
        padthai = (CheckBox) findViewById(R.id.padtai_box);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, OrderActivity.class);

                if (kapao.isChecked()){
                    mOrderList[count] = "Krapao  " + getString(R.string.kapao_price);
                    count++;
                }
                if (rice.isChecked()){
                    mOrderList[count] = "Fried rice  " + getString(R.string.rice_price);
                    count++;
                }
                if (egg.isChecked()){
                    mOrderList[count] = "Omelet  " + getString(R.string.egg_price);
                    count++;
                }
                if (padthai.isChecked()){
                    mOrderList[count] = "Padtai  " + getString(R.string.padthai_price);
                    count++;
                }

                for(int i = 0; i < 4; i++){
                    intent.putExtra(EXTRA_MESSAGE + i, mOrderList[i]);
                }
                intent.putExtra(EXTRA_MESSAGE + "_count", count);
                startActivityForResult(intent, 1);

            }
        });
    }

    public void addListenerOnButtonClick(View view) {
        CheckBox checkbox = findViewById(view.getId());

        if(checkbox.isChecked()){
            String Article = " a ";
            String Name = checkbox.getText().toString().toLowerCase();
            switch (Name.charAt(0)){
                case 'a' : case 'e' : case 'i' : case 'o' : case 'u' : Article = " an " ;
                    break;
            }
            displayToast("You ordered" + Article + Name.toLowerCase());
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

}