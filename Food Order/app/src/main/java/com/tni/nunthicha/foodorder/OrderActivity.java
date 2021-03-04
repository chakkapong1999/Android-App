package com.tni.nunthicha.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    private serverconnect connect = new serverconnect();
    private jsonClass jsondata = new jsonClass();

    private int id[] = new int[4];
    private int amount[] = new int[4];
    public static String EXTRA_MESSAGE_REPLY = "com.tni.nunthicha.foodorder.extra.MESSAGE.REPLY";
    public String mOrderList[] = new String[4];

    public int count;
    int countAmount = 0;
    int countAmount1 = 0;
    int countAmount2 = 0;
    int countAmount3 = 0;

    int countprice = 60;
    int countprice1 = 45;
    int countprice2 = 40;
    int countprice3 = 65;
    int showcount = 0;

    private String order0 = "didn't pick";
    private String order1 = "didn't pick";
    private String order2 = "didn't pick";
    private String order3 = "didn't pick";
    private String tableNum = "not set";
    private String phoneNum = "not set";
    private String note = "-";
    private String amount0 = "";
    private String amount1 = "";
    private String amount2 = "";
    private String amount3 = "";
    private String Total = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();

        TextView order = findViewById(R.id.order0);
        TextView order1 = findViewById(R.id.order1);
        TextView order2 = findViewById(R.id.order2);
        TextView order3 = findViewById(R.id.order3);


        id[0] = R.id.order0;
        id[1] = R.id.order1;
        id[2] = R.id.order2;
        id[3] = R.id.order3;

        amount[0] = R.id.Amount;
        amount[1] = R.id.Amount1;
        amount[2] = R.id.Amount2;
        amount[3] = R.id.Amount3;

        count = intent.getIntExtra(MainActivity.EXTRA_MESSAGE + "_count", 0);

        for (int i = 0; i < 4; i++) {
            mOrderList[i] = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + i);
            String message = mOrderList[i];
            TextView textView = findViewById(id[i]);
            textView.setText(message);

            if (!order.getText().toString().isEmpty()) {
                Button a = findViewById(R.id.add);
                Button m = findViewById(R.id.minus);
                TextView amount = findViewById(R.id.Amount);
                amount.setVisibility(View.VISIBLE);
                a.setVisibility(View.VISIBLE);
                m.setVisibility(View.VISIBLE);

            }
            if (!order1.getText().toString().isEmpty()) {
                Button a = findViewById(R.id.add1);
                Button m = findViewById(R.id.minus1);
                TextView amount = findViewById(R.id.Amount1);
                amount.setVisibility(View.VISIBLE);
                a.setVisibility(View.VISIBLE);
                m.setVisibility(View.VISIBLE);
            }
            if (!order2.getText().toString().isEmpty()) {
                Button a = findViewById(R.id.add2);
                Button m = findViewById(R.id.minus2);
                TextView amount = findViewById(R.id.Amount2);
                amount.setVisibility(View.VISIBLE);
                a.setVisibility(View.VISIBLE);
                m.setVisibility(View.VISIBLE);
            }
            if (!order3.getText().toString().isEmpty()) {
                Button a = findViewById(R.id.add3);
                Button m = findViewById(R.id.minus3);
                TextView amount = findViewById(R.id.Amount3);
                amount.setVisibility(View.VISIBLE);
                a.setVisibility(View.VISIBLE);
                m.setVisibility(View.VISIBLE);
            }
        }
    }

    public void countUp(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.add:
                countAmount++;
                ((TextView) findViewById(R.id.Amount)).setText(String.valueOf(countAmount));
                showcount = countprice3 * countAmount3 + countprice2 * countAmount2 + countprice1 * countAmount1 + countprice * countAmount;
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;
            case R.id.add1:
                countAmount1++;
                ((TextView) findViewById(R.id.Amount1)).setText(String.valueOf(countAmount1));
                showcount = countprice3 * countAmount3 + countprice2 * countAmount2 + countprice1 * countAmount1 + countprice * countAmount;
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;
            case R.id.add2:
                countAmount2++;
                ((TextView) findViewById(R.id.Amount2)).setText(String.valueOf(countAmount2));
                showcount = countprice3 * countAmount3 + countprice2 * countAmount2 + countprice1 * countAmount1 + countprice * countAmount;
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;
            case R.id.add3:
                countAmount3++;
                ((TextView) findViewById(R.id.Amount3)).setText(String.valueOf(countAmount3));
                showcount = countprice3 * countAmount3 + countprice2 * countAmount2 + countprice1 * countAmount1 + countprice * countAmount;
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;
        }
    }

    public void countDown(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.minus:
                if (countAmount > 0) {
                    countAmount -= 1;
                    showcount -= countprice;
                } else {
                    countAmount = 0;
                    showcount = showcount ;
                }
                ((TextView) findViewById(R.id.Amount)).setText(String.valueOf(countAmount));
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;

            case R.id.minus1:
                if (countAmount1 > 0) {
                    countAmount1 -= 1;
                    showcount -= countprice1;
                } else {
                    countAmount1 = 0;
                    showcount = showcount;
                }
                ((TextView) findViewById(R.id.Amount1)).setText(String.valueOf(countAmount1));
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;

            case R.id.minus2:
                if (countAmount2 > 0) {
                    countAmount2 -= 1;
                    showcount -= countprice2;
                } else {
                    countAmount2 = 0;
                    showcount = showcount;
                }
                ((TextView) findViewById(R.id.Amount2)).setText(String.valueOf(countAmount2));
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;

            case R.id.minus3:
                if (countAmount3 > 0) {
                    countAmount3 -= 1;
                    showcount -= countprice3;
                } else {
                    countAmount3 = 0;
                    showcount = showcount;
                }
                ((TextView) findViewById(R.id.Amount3)).setText(String.valueOf(countAmount3));
                ((TextView) findViewById(R.id.price_value)).setText(String.valueOf(showcount));
                break;
        }
    }


    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void place_onclick(View view) {
        EditText tableTextInput = findViewById(R.id.tablenum);
        EditText phoneTextInput = findViewById(R.id.phonenum);
        EditText noteTextInput = findViewById(R.id.note);
        TextView amount_0 = findViewById(R.id.Amount);
        TextView amount_1 = findViewById(R.id.Amount1);
        TextView amount_2 = findViewById(R.id.Amount2);
        TextView amount_3 = findViewById(R.id.Amount3);
        TextView totalP = findViewById(R.id.price_value);
        TextView order_0 = findViewById(R.id.order0);
        TextView order_1 = findViewById(R.id.order1);
        TextView order_2 = findViewById(R.id.order2);
        TextView order_3 = findViewById(R.id.order3);

        if (!order_0.getText().toString().toLowerCase().equals("")){
            order0 = order_0.getText().toString();
        }
        if (!order_1.getText().toString().toLowerCase().equals("")){
            order1 = order_1.getText().toString();
        }
        if (!order_2.getText().toString().toLowerCase().equals("")){
            order2 = order_2.getText().toString();
        }
        if (!order_3.getText().toString().toLowerCase().equals("")){
            order3 = order_3.getText().toString();
        }
        if (!tableTextInput.getText().toString().toLowerCase().equals("")) {
            tableNum = tableTextInput.getText().toString();
        }
        if (!phoneTextInput.getText().toString().toLowerCase().equals("")) {
            phoneNum = phoneTextInput.getText().toString();
        }
        if (!noteTextInput.getText().toString().toLowerCase().equals("")) {
            note = noteTextInput.getText().toString();
        }
        if (!amount_0.getText().toString().toLowerCase().equals("")) {
            amount0 = amount_0.getText().toString();
        }
        if (!amount_1.getText().toString().toLowerCase().equals("")) {
            amount1 = amount_1.getText().toString();
        }
        if (!amount_2.getText().toString().toLowerCase().equals("")) {
            amount2 = amount_2.getText().toString();
        }
        if (!amount_3.getText().toString().toLowerCase().equals("")) {
            amount3 = amount_3.getText().toString();
        }
        if (!totalP.getText().toString().toLowerCase().equals("")) {
            Total = totalP.getText().toString();
        }

        jsondata.setRoot("Table : " + tableNum)
                .addString("1",order0 +" -> "+ amount0)
                .addString("2",order1 +" -> "+ amount1)
                .addString("3",order2 +" -> "+ amount2)
                .addString("4",order3 +" -> "+ amount3)
                .addString("Total", Total + " THB")
                .addString("phone number", phoneNum)
                .addString("note", note);

        connect.addData("test", jsondata.get());
        displayToast("Place Ordered");
    }
}




