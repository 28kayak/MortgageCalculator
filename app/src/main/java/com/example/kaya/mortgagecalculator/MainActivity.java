package com.example.kaya.mortgagecalculator;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{

    int intRate;
    double monthlyPayment = 0;
    int selectedLoanTerm;
    EditText borrowedAmount;
    TextView payment;
    TextView rateLabel;
    String amount;
    SeekBar interestRate;
    RadioGroup loanTerm;
    CheckBox tax;
    CheckBox insurance;
    String decimalFormat = "\\d+\\.\\d+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        borrowedAmount = (EditText)findViewById(R.id.borrowedAmount);
        payment = (TextView) findViewById(R.id.payment);
        interestRate = (SeekBar) findViewById(R.id.interestRate);
        rateLabel = (TextView) findViewById(R.id.rateLabel);
        loanTerm = (RadioGroup) findViewById(R.id.loanTerm);//need to check how the value is returned and stored.
        tax = (CheckBox) findViewById(R.id.tax);
        insurance = (CheckBox)findViewById(R.id.insurance);


        //set seek bar listener
        interestRate.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser)
                        {
                            intRate = interestRate.getProgress();
                            rateLabel.setText("Interest Rate "  +  intRate + " % ");
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //called when touched of seekbar
                        System.out.print("in OnStartTracking seek bar");
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //Called when untouched of seekbar
                    }
                }//new Seekbar.onSeekBarChangeListener
        );//setOnSeekBarChangeListener()
    }//onCreate
    public void calculate(View v)
    {
        monthlyPayment = 0; //init result valaible

        //should be called when calculate button is clicked
        int selectedLotnTerm = loanTerm.getCheckedRadioButtonId();
        RadioButton selected = (RadioButton) findViewById(selectedLotnTerm);

        if(Pattern.matches(decimalFormat, borrowedAmount.getText()) && selectedLotnTerm != -1)
        {
            //payment.setText("Amount: " + borrowedAmount.getText())
            payment.setText("id: " + getLoanTerm(selected.getText()+ ""));
            int N = getLoanTerm(selected.getText()+ "");
            double P = Double.parseDouble(borrowedAmount.getText()+"");

            if(intRate == 0)
            {
                //interest Rate is 0 %
                //calculation: monthlyPayment = (borrowedAmount / loanTerms ) + tax
                monthlyPayment = (P / N);
                if(tax.isChecked())
                {
                    if(insurance.isChecked())
                    {
                        //insurance + tax = 2(P*0.001)
                        monthlyPayment = monthlyPayment + 2*(P * 0.001);
                    }
                    else
                    {
                        //only tax is checked
                        monthlyPayment = monthlyPayment + (P * 0.001);

                    }

                }//tax.isChecked()
                else
                {
                    if(insurance.isChecked())
                    {
                        //tax is not checked and insurance is checked
                        monthlyPayment= monthlyPayment + (P * 0.001);
                    }
                    else
                    {
                        //both are not checked
                        // no change on monthlyPayment
                    }
                }
                String result = String.format("%1$.3f /m", monthlyPayment);
                payment.setTextColor(Color.BLUE);
                payment.setText("Payment: " + result);
            }
        }//if(Pattern.matches(decimalFormat, borrowedAmount.getText()))
        else
        {

            payment.setText("wrong input");
            payment.setTextColor(Color.RED);
        }



        //payment.setText(selected.getText());

       // payment.setText(Integer.valueOf(interestRate.getProgress()));



    }//calcualte

    //helper function to get int type loan term
    public int getLoanTerm(String text)
    {
        int result = 0;
        String[] temp = text.split(" ");
        result = Integer.parseInt(temp[0]);
        return result;
    }//getLoanTerm
}//class
