package com.example.lumpy.fibtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.fibonacciToCalculate);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    displayFibonacci(calculateFibonacci());
                    /**TODO: Add clearing and redoing the calculation*/
                    return true;
                }
                return false;
            }
        });

    }

    private BigInteger calculateFibonacci()
    {
        //Really slow version of fib
        EditText editText = (EditText) findViewById(R.id.fibonacciToCalculate);

        //Check integer values with try catch
        try {
            int nthFib =  Integer.parseInt(editText.getText().toString());
            BigInteger a = BigInteger.ZERO;
            BigInteger b = BigInteger.ONE;

            for (int i = 0; i < nthFib; i++) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
            return a;
            /**TODO: Add focus change to remove keyboard
             * */


        } catch (NumberFormatException e) {
            Toast.makeText(this,"Number is too large to handle or in an incorrect format", Toast.LENGTH_SHORT).show();
            return BigInteger.ZERO;
        }

    }

    //small helper function for
    private void displayFibonacci(BigInteger nthFibonacci){
        TextView resultTextView = (TextView) findViewById(
                R.id.fibonacciResult);
        resultTextView.setText("" + nthFibonacci);
    }
}
