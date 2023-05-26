package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.User;
import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class MainScreenActivity extends AppCompatActivity {
    public static final int delay = 800;
    public static final Handler handler = new Handler();
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.main_screen_activity);
    }
    @Override
    public void onResume() {
        super.onResume();
        //event do automatic advance to the sign in screen!
        handler.postDelayed(() -> {
            //Creating a fragment manager to change automatically from main fragment to the terms fragment.
            Intent intent = new Intent(this, LoginActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            AppDatabase db = AppDatabase.getInstance(this);
            this.startActivity(intent, bundle);
            finish();
        }, delay);
    }

}