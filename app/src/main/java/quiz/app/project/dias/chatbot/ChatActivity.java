package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class ChatActivity extends AppCompatActivity {
    Button btnConfig, btnChatLogout;
    FloatingActionButton btnAddMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.chat_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.btnConfig= findViewById(R.id.btnConfig);
        this.btnAddMsg = findViewById(R.id.btnAddMsg);
        this.btnChatLogout = findViewById(R.id.btnChatLogout);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, ConfigActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                ChatActivity.this.startActivity(intent, bundle);
            }
        });
        btnAddMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, NewChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                ChatActivity.this.startActivity(intent, bundle);
            }
        });
        btnChatLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(ChatActivity.this)
                        .setTitle("Deseja efetuar o logout?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ChatActivity.this, LoginActivity.class);
                                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                                ChatActivity.this.startActivity(intent, bundle);
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Deseja sair da aplicação?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}