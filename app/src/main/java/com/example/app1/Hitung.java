package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Hitung extends AppCompatActivity {
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};

    private int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};

    private TextView txtscreen;

    private boolean lastNumeric;

    private boolean stateError;

    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);
        this.txtscreen = (TextView) findViewById(R.id.txtscreen);
        setNumericOnClickListener();
        setOperatorOnClickListener();
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (stateError) {
                    txtscreen.setText(button.getText());
                    stateError = false;
                } else {
                    txtscreen.append(button.getText());
                }
                lastNumeric = true;
            }
        };

        for (int id :numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastNumeric && !stateError) {
                    Button button = (Button) view;
                    txtscreen.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastNumeric && !stateError && !lastDot) {
                    txtscreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtscreen.setText("");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });

        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        if (lastNumeric && !stateError) {
            String txt = txtscreen.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                txtscreen.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {
                txtscreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}