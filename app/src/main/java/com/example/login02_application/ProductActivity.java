package com.example.login02_application;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;

import java.io.UnsupportedEncodingException;

public class ProductActivity extends AppCompatActivity {
    EditText jinputMessage;
    TextView jinputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        jinputMessage = findViewById(R.id.xinputMessage);
        jinputCount = findViewById(R.id.xinputCount);

        Button jsendButton = findViewById(R.id.xsendButton);
        jsendButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String message = jinputMessage.getText().toString();
                Toast.makeText(getApplicationContext(), "전송할 메시지 \n\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        Button jcloseButton = findViewById(R.id.xcloseButton);
        jcloseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                byte[] bytes = null;
                try {
                    bytes = str.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    jinputCount.setText(strCount + " / 80바이트");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }

            public void beforeTextChanged(CharSequence str, int start, int count, int after) {

            }

            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                try {
                    byte[] strBytes = str.getBytes("KSC5601");
                    if (strBytes.length > 80) {
                        strEditable.delete(strEditable.length()-2,strEditable.length()-1);
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        jinputMessage.addTextChangedListener(watcher);
    }
}