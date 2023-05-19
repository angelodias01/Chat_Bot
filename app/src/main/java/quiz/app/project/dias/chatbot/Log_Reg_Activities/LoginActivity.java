package quiz.app.project.dias.chatbot.Log_Reg_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        TextView lblCreateAcc = findViewById(R.id.lblCreateAcc);

        lblCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle();
                startActivity(intent, bundle);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database code
                AppDatabase db = Room.databaseBuilder(LoginActivity.this, AppDatabase.class,"AppDatabase").build();
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
                            Toast.makeText(LoginActivity.this, "Início de sessão com êxito!",
                                    Toast.LENGTH_SHORT).show();
                            executor.shutdown();
                            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle();
                            LoginActivity.this.startActivity(intent, bundle);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "O nome de utilizador e a palavra-passe não correspondem!",
                                    Toast.LENGTH_SHORT).show();
                            if (username.equals("")) {
                                tbUsername.setError("Introduza o seu nome de utilizador!");
                                tbUsername.requestFocus();
                            } else if (password.equals("")) {
                                tbPassword.setError("Introduza a sua palavra-passe!");
                                tbPassword.requestFocus();
                            } else {
                                tbUsername.setError("O nome de utilizador e a palavra-passe não correspondem!");
                                tbPassword.setError("O nome de utilizador e a palavra-passe não correspondem!");
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
        new AlertDialog.Builder(this)
                .setTitle("Deseja sair da aplicação?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

}