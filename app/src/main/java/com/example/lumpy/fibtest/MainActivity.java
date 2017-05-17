package com.example.lumpy.fibtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultTextView = (TextView) findViewById(R.id.fibonacciResult);
        resultTextView.setMovementMethod(new ScrollingMovementMethod());

        final EditText editText = (EditText) findViewById(R.id.fibonacciToCalculate);


        //Clear previous input
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Check if the done button has been pressed
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    displayFibonacci(calculateFibonacci());

                    //Removes keyboard after number is entered
                    View view = MainActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    //Calculates the Fibonacci value
    private BigInteger calculateFibonacci() {
        //Really slow version of fib
        EditText editText = (EditText) findViewById(R.id.fibonacciToCalculate);

        //Check integer values with try catch
        try {
            int nthFib = Integer.parseInt(editText.getText().toString());

            //Start Calculation time
            long startTime = System.nanoTime();

            //Begin algorithm
            BigInteger a = BigInteger.ZERO;
            BigInteger b = BigInteger.ONE;
            BigInteger c = BigInteger.ZERO;

            for (int i = 0; i < nthFib; i++) {
                c = a.add(b);
                a = b;
                b = c;
            }

            //Show Calculation time
            long totalTime = (System.nanoTime() - startTime) / 1000000;
            Toast.makeText(MainActivity.this, "Calculation took " + totalTime + "ms", Toast.LENGTH_LONG).show();

            return a;

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Number is too large to handle or in an incorrect format", Toast.LENGTH_SHORT).show();
            return BigInteger.ZERO;
        }

    }

    //small helper function for displaying result
    private void displayFibonacci(BigInteger nthFibonacci) {
        TextView resultTextView = (TextView) findViewById(
                R.id.fibonacciResult);
        resultTextView.setText("" + nthFibonacci);
    }
}
