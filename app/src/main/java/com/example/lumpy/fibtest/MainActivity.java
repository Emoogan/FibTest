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

                    //Removes keyboard after number is entered
                    View view = MainActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    displayFibonacci(calculateFibonacci());

                    return true;
                }
                return false;
            }
        });

    }

    //Calculates the Fibonacci value
    private BigInteger calculateFibonacci() {
        //Matrix power calculation
        EditText editText = (EditText) findViewById(R.id.fibonacciToCalculate);

        //Check integer values with try catch
        try {
            int nthFib = Integer.parseInt(editText.getText().toString());

            //Start Calculation time
            long startTime = System.nanoTime();

            //Begin algorithm
            BigInteger a = BigInteger.ZERO;
            BigInteger[] matrix = {BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO};
            a = matrixPow(matrix, nthFib)[1];

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

    //matrix power function for bigintegers
    private static BigInteger[] matrixPow(BigInteger[] matrix, int n) {

        BigInteger[] result = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};
        while (n != 0) {  // Exponentiation by squaring
            if (n % 2 != 0)
                result = matrixMultiply(result, matrix);
            n /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result;
    }

    // Multiplies two matrices.
    private static BigInteger[] matrixMultiply(BigInteger[] x, BigInteger[] y) {
        return new BigInteger[] {
                (x[0].multiply(y[0])).add((x[1].multiply(y[2]))),
                (x[0].multiply(y[1])).add((x[1].multiply(y[3]))),
                (x[2].multiply(y[0])).add((x[3].multiply(y[2]))),
                (x[2].multiply(y[1])).add((x[3].multiply(y[3])))

        };
    }

}
