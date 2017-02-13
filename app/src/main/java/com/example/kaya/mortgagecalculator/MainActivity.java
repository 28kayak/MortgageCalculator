package com.example.kaya.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    EditText borrowedAmount;
    TextView payment;
    SeekBar interestRate;
    RadioGroup loanTerm;
    CheckBox tax;
    CheckBox insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void calculate(View v)
    {

        borrowedAmount = (EditText)findViewById(R.id.borrowedAmount);
        payment = (TextView) findViewById(R.id.payment);
        interestRate = (SeekBar) findViewById(R.id.interestRate);
        loanTerm = (RadioGroup) findViewById(R.id.loanTerm);//need to check how the value is returned and stored.
        tax = (CheckBox) findViewById(R.id.tax);
        insurance = (CheckBox)findViewById(R.id.insurance);



        //borrowedAmount.addTextChangedListener(this);
        payment.setText(interestRate.getX());
        /


    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int before, int count)
    {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
        String amount = s.toString();

        double decimalAmount;
        try
        {
            decimalAmount = Double.parseDouble(amount);
            payment.setText(amount);
        }
        catch(NumberFormatException nan)
        {
            borrowedAmount.setText("Wrong Input!!");
        }
    }
}
