package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class MainScreenActivity extends AppCompatActivity {
    public static final int delay = 1000;
    public static final Handler handler = new Handler();

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
            this.startActivity(intent, bundle);
        }, delay);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Do You Want To Exit The App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}