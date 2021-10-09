package com.example.common_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_web;
    Button btn_phone;
    Button btn_email;
    Button btn_note;
    EditText et_data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_web=findViewById(R.id.btn_web);
        et_data=findViewById(R.id.et_data);
        btn_phone=findViewById(R.id.btn_phone);
        btn_email=findViewById(R.id.btn_email);
        btn_note=findViewById(R.id.btn_text);



        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(et_data.getText().toString());
            }
        });

        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(et_data.getText().toString());
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address[]=new String[1];
                address[0]=et_data.getText().toString();
                composeEmail(address,"hello Le Quang ");

            }
        });

        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeMmsMessage(et_data.getText().toString(),"hello");
            }
        });

    }

    private void openWebPage(String url) {
        if(!url.startsWith("http://")||!url.startsWith("https://"))
            url="http://"+url;
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
       // intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void composeMmsMessage(String phoneNum,String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+phoneNum));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
     //   intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}