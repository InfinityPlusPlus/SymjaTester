package com.example.symjatester;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;

public class MainActivity extends AppCompatActivity {
    // private static final String TAG = "MainActivity";

    private EditText inputFunction;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFunction = findViewById(R.id.input_function);
        Button calculateDerivative = findViewById(R.id.calculate_derivative);
        result = findViewById(R.id.result);

        calculateDerivative.setOnClickListener(v -> {
            String function = inputFunction.getText().toString();
            String derivative = calculateDerivative(function);
            result.setText(derivative);
        });

        try {
            LoggerFix.fix();
            // ExprEvaluator exprEvaluator = new ExprEvaluator();
            // Log.d(TAG, String.valueOf(exprEvaluator.eval("test")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String calculateDerivative(String function) {
        ExprEvaluator evaluator = new ExprEvaluator();
        IExpr x = evaluator.parse("x");
        IExpr inputExpr = evaluator.parse(function);
        IExpr derivativeExpr = F.D(inputExpr, x);
        IExpr result = evaluator.eval(derivativeExpr);
        return result.toString();
    }
}