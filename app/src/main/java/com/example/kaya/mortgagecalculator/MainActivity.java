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

    double intRate;
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
                            intRate = interestRate.getProgress()/ 10;
                            //intRate = intRate / 10;
                            rateLabel.setText("Interest Rate "  +  intRate + " % ");
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        //called when touched of seekbar
                        //System.out.print("in OnStartTracking seek bar");
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
            Double tempN = new Double( getLoanTerm(selected.getText()+ ""));
            double N = getLoanTerm(selected.getText()+ "") * 1.00;
            double P = Double.parseDouble(borrowedAmount.getText()+"");
            double T = P * 0.001;

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
                        monthlyPayment = monthlyPayment + 2*T;
                    }
                    else
                    {
                        //only tax is checked
                        monthlyPayment = monthlyPayment + T;
                    }

                }//tax.isChecked()
                else
                {
                    if(insurance.isChecked())
                    {
                        //tax is not checked and insurance is checked
                        monthlyPayment= monthlyPayment + T;
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
            }//if(intRate == 0)
            else
            {
                //Double tempJ  = new Double(intRate);
                double J = intRate/1200.00;

                double denominator =1.00-Math.pow((1.00 + J), -N);
                double numerator = J;
                monthlyPayment = P * ( numerator / denominator );
                if(tax.isChecked())
                {
                    if(insurance.isChecked())
                    {
                        //insurance + tax = 2(P*0.001)
                        monthlyPayment = monthlyPayment + 2*T;
                    }
                    else
                    {
                        //only tax is checked
                        monthlyPayment = monthlyPayment + T;
                    }

                }//tax.isChecked()
                else
                {
                    if(insurance.isChecked())
                    {
                        //tax is not checked and insurance is checked
                        monthlyPayment= monthlyPayment + T;
                    }
                    else
                    {
                        //both are not checked
                        // no change on monthlyPayment
                    }
                }//if(tax.isChecked())
                String result = String.format("%1$.3f /m", monthlyPayment);
                payment.setTextColor(Color.BLUE);
                payment.setText("Payment: " + result);

            }//else of if(intRate == 0)
        }//if(Pattern.matches(decimalFormat, borrowedAmount.getText()))
        else
        {

            payment.setText("wrong input");
            payment.setTextColor(Color.RED);
        }
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
