package com.swufestu.second;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView showout;
    private EditText input1,input2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.test11);

        //获取用户输入
        showout = findViewById(R.id.screen);
        input1 = findViewById(R.id.writein);
        input2 = findViewById(R.id.writein1);
        Button btn1 = findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = input1.getText().toString();
                String height = input2.getText().toString();
                Double h =Double.parseDouble(height);
                Double w =Double.parseDouble(weight);

                Double result = w/(h*h);
                NumberFormat ddf1=NumberFormat.getNumberInstance() ;
                ddf1.setMaximumFractionDigits(2);
                if(result<18.5){
                    String BMI= "BMI:"+ ddf1.format(result)+"偏瘦，注意营养均衡";
                    showout.setText(BMI);
                }
                else if(result<24){
                    String BMI= "BMI:"+ ddf1.format(result)+"正常，继续保持";
                    showout.setText(BMI);
                }
                else if(result<27){
                    String BMI= "BMI:"+ ddf1.format(result)+"超重，控制饮食";
                    showout.setText(BMI);
                }
                else{
                    String BMI= "BMI:"+ ddf1.format(result)+"肥胖！必须减肥了";
                    showout.setText(BMI);
                }

            }
        });
    }
}