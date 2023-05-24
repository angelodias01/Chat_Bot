package quiz.app.project.dias.chatbot.Log_Reg_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quiz.app.project.dias.chatbot.ChatActivity;
import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.User;
import quiz.app.project.dias.chatbot.Database.UserDao;
import quiz.app.project.dias.chatbot.R;

public class RegisterActivity extends AppCompatActivity {
    private static final String userId = "userId";
    private EditText tbUsername, tbPassword;
    private String username, password, restoreUsername, restorePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.register_activity);

        if (savedInstanceState != null) {
            restoreUsername = savedInstanceState.getString(username);
            restorePassword = savedInstanceState.getString(password);
        }
        this.tbUsername = findViewById(R.id.tbUsername);
        this.tbPassword = findViewById(R.id.tbPassword);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        tbUsername = findViewById(R.id.tbUsername);
        tbPassword = findViewById(R.id.tbPassword);

        username = String.valueOf(tbUsername);
        password = String.valueOf(tbPassword);

        savedInstanceState.putString(username, restoreUsername);
        savedInstanceState.putString(password, restorePassword);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tbUsername = findViewById(R.id.tbUsername);
        tbPassword = findViewById(R.id.tbPassword);
        TextView lblLogin = findViewById(R.id.lblLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        lblLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle();
            startActivity(intent, bundle);
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database code
                AppDatabase db = AppDatabase.getInstance(RegisterActivity.this);;

                username = tbUsername.getText().toString();
                password = tbPassword.getText().toString();

                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    Handler handlers = new Handler(Looper.getMainLooper());

                    // Post a runnable on the main/UI thread
                    handlers.post(() -> {
                        if(Objects.equals(username, "")|| Objects.equals(password, "")){
                            if(Objects.equals(username, "")){
                                tbUsername.setError("Introduza o seu nome de utilizador!");
                                tbUsername.requestFocus();
                             }else if(Objects.equals(password, "")){
                                tbPassword.setError("Introduza a sua palavra-passe!");
                                tbPassword.requestFocus();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Conta criada com êxito!",
                                    Toast.LENGTH_SHORT).show();
                            User newUser = new User(username,password);
                            AppDatabase.getInstance(RegisterActivity.this).getUserDao().insertAll(newUser);
                            executor.shutdown();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle();
                            finish();
                            startActivity(intent, bundle);
                        }
                    });
                });
            }
        });
    }
}