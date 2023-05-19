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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quiz.app.project.dias.chatbot.ChatActivity;
import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.User;
import quiz.app.project.dias.chatbot.Database.UserDao;
import quiz.app.project.dias.chatbot.R;

public class LoginActivity extends AppCompatActivity {
    private static final String userId = "userId";
    private EditText tbUsername, tbPassword;
    private Button btnLogin;
    private String username, password, restoreUsername, restorePassword;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.login_activity);

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
        this.btnLogin = findViewById(R.id.btnRegister);
        tbUsername = findViewById(R.id.tbUsername);
        tbPassword = findViewById(R.id.tbPassword);
        TextView lblCreateOne = findViewById(R.id.lblCreateOne);

        lblCreateOne.setOnClickListener(view1 -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        });

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database code
                AppDatabase db = Room.databaseBuilder(LoginActivity.this, AppDatabase.class, "AppDatabase").build();
                UserDao userDao = db.getUserDao();

                username = tbUsername.getText().toString();
                password = tbPassword.getText().toString();

                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    User user = userDao.getUserByUsernameAndPassword(username, password);
                    // Create a handler associated with the main/UI thread
                    Handler handlers = new Handler(Looper.getMainLooper());

                    // Post a runnable on the main/UI thread
                    handlers.post(() -> {
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "Login Successful!",
                                    Toast.LENGTH_SHORT).show();
                            executor.shutdown();
                            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                            startActivity(intent);
                            bundle = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle();
                            LoginActivity.this.startActivity(intent, bundle);
                            finishActivity(1);
                        } else {
                            Toast.makeText(LoginActivity.this, "Email and Password didn't match!",
                                    Toast.LENGTH_SHORT).show();
                            if (username.equals("")) {
                                tbUsername.setError("Please insert your email!");
                                tbUsername.requestFocus();
                            } else if (password.equals("")) {
                                tbPassword.setError("Please insert your password!");
                                tbPassword.requestFocus();
                            } else {
                                tbUsername.setError("Email and password didn't match!");
                                tbPassword.setError("Email and password didn't match!");
                                tbUsername.requestFocus();
                            }
                        }
                    });
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}