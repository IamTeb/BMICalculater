package com.example.codetribe.bmicalculater;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText height;
    double sum;
    String msg;
    TextView txt;
    double weight;
    TextView txt2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt = (TextView) findViewById(R.id.BMICalc);
        txt2 = (TextView) findViewById(R.id.txtCat);
    }
    @TargetApi(Build.VERSION_CODES.N)
    public void onRadioButtonChecked (View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        txt2.setText(msg);

        switch (view.getId())
        {
            case R.id.rdb1:
                weight = 50.0;
                if (checked)
                    sum = 0.00;
                calculateBMI();
                break;
            case R.id.rdb2:
                weight = 64.0;
                if (checked)
                    sum = 0.00;
                calculateBMI();
                break;
            case R.id.rdb3:
                weight =  100.0;
                calculateBMI();
                break;
        }

    }

    public void calculateBMI()
    {
        height = (EditText) findViewById(R.id.TextHeight);
        String height1 = height.getText().toString();

        Double finalHeight = Double.parseDouble(height1);

        Double newHeightValueMeters = finalHeight / 100.00;
        Double height = Math.pow(newHeightValueMeters, 2);
        final Double userBMI = (weight / height);



        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(sum<userBMI)
                {
                    try
                    {
                        Thread.sleep(15);
                    }catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                    txt.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            txt.setText(""+Math.round(sum*100.00)/100.00);

                        }
                    });
                    sum+=1.1;
                }
            }
        }).start();


        if (userBMI <= 18.5)
        {
            txt2.setText("Underweight");
            txt2.setTextColor(Color.RED);
        }
        else if (userBMI > 18.5 && userBMI < 24.5)
        {
            txt2.setText("Healthy weight");
            txt2.setTextColor(Color.GREEN);
        }
        else if (userBMI > 24.5 && userBMI < 29.9)
        {
            txt2.setText("Overweight");
            txt2.setTextColor(Color.CYAN);
        }
        else if (userBMI > 29.9)
        {
            txt2.setText("Obese");
            txt2.setTextColor(Color.RED);

        }
        else
        {
            txt2.setText("Unknown Weight");
        }


    }

}
