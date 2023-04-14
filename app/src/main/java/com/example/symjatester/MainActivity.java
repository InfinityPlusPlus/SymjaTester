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
    }

    private String calculateDerivative(String function) {
        ExprEvaluator evaluator = new ExprEvaluator();
        IExpr inputExpr = evaluator.parse(function);
        IExpr derivativeExpr = F.D(inputExpr, F.x);
        IExpr simplifiedDerivative = evaluator.eval(derivativeExpr);
        return simplifiedDerivative.toString();
    }
}