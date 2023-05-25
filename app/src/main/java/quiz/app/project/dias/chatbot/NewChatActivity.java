package quiz.app.project.dias.chatbot;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import quiz.app.project.dias.chatbot.Database.Chat;

public class NewChatActivity extends AppCompatActivity {
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.new_chat_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.btnBack = findViewById(R.id.btnBackConfig);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewChatActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(NewChatActivity.this).toBundle();
                finish();
                startActivity(intent, bundle);
            }
        });
    }
}
