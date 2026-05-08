package com.example.myapplication;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity{

    EditText requestEditText;
    Button send;
    static ImageView replyBox,requestBox;
    static TextView reply,request;
    static RelativeLayout page;

    private static int number=0;

    public static ImageView getReplyBox() {
        return replyBox;
    }

    public static TextView getReply() {
        return reply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        Bundle extras = getIntent().getExtras();
        String phone = null;
        if (extras != null) {
            phone = extras.getString("user");
        }



        requestEditText=findViewById(R.id.request_edit_text);
        requestBox=findViewById(R.id.request_box);
        request=findViewById(R.id.request_text);
        send=findViewById(R.id.send_request);
        reply=findViewById(R.id.awnser_text);
        replyBox=findViewById(R.id.anwser_box);
        page= (RelativeLayout) findViewById(R.id.request_page);
        String finalPhone = phone;

        
        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(number>=1){
                    request.setText(request.getText().toString() + "\n" +requestEditText.getText().toString() );
                    Options.findUser(finalPhone).getRequests().add(new Request(requestEditText.getText().toString(),""));
                    requestEditText.setText("");
                    RequestThread thread=new RequestThread(){
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        sleep(2000);
                                        Administrator.sendReply();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }

                                }
                            });
                        }
                    };
                    thread.start();
                } else {
                Options.findUser(finalPhone).getRequests().add(new Request(requestEditText.getText().toString(),""));
                requestBox.setVisibility(View.VISIBLE);
                request.setText(requestEditText.getText().toString());
                requestEditText.setText("");
                Toast.makeText(RequestActivity.this, "Your massage send successfully", Toast.LENGTH_SHORT).show();
                RequestThread thread=new RequestThread(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    sleep(2000);
                                    Administrator.sendReply();
                                    replyBox.setVisibility(View.VISIBLE);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        });
                    }
               };
                thread.start();
            }
        }

    });}



    public static void setReply(TextView reply) {
        RequestActivity.reply = reply;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        RequestActivity.number = number;
    }

    public static Context getContext(){
        return RequestActivity.getContext();
    }

    public static RelativeLayout getPage() {
        return page;
    }


}
