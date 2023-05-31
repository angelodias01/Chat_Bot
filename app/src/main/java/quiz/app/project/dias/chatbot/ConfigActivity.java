package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.ChatDao;
import quiz.app.project.dias.chatbot.Database.MessagesDao;
import quiz.app.project.dias.chatbot.Database.UserDao;
import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class ConfigActivity extends AppCompatActivity {
    private Button btnLimpar, btnBackConfig, btnLogoutConfig;
    private static final String userId = "userId";
    public int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getInt(this.userId, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.btnLimpar = findViewById(R.id.btnLimpar);
        this.btnBackConfig = findViewById(R.id.btnBackConfig);
        this.btnLogoutConfig =  findViewById(R.id.btnLogoutConfig);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(ConfigActivity.this, AppDatabase.class, "AppDatabase").build();
                UserDao userDao = db.getUserDao();
                ChatDao chatDao = db.getChatDao();
                MessagesDao messagesDao = db.getMessageDao();

                new AlertDialog.Builder(ConfigActivity.this)
                        .setTitle("Deseja limpar os dados da aplicação?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                executor.execute(() -> {
                                    // TODO Configs:
                                    Chat chatId = chatDao.getChatIdByUser(userID);
                                    //TODO next code im not sure if it works!!
                                    messagesDao.delete(messagesDao.getMessagesByChatId(chatId.getChatId()));
                                    chatDao.delete(chatDao.getChatIdByUser(userID));
                                    //TODO set by userid!!
                                    List chatList = chatDao.getChatById(userID);
                                    //List messageList = messagesDao.getMessagesByChatId(chatDao.getChatIdByUser(userID));

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //&& messageList != null
                                            if (chatList != null ) {
                                                Toast.makeText(ConfigActivity.this, "Dados limpos com sucesso!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ConfigActivity.this,MainScreenActivity.class);
                                                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
                                                ConfigActivity.this.startActivity(intent, bundle);
                                                finishAffinity();
                                            } else {
                                                Toast.makeText(ConfigActivity.this, "Não foi possível limpar os dados!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    executor.shutdown();
                                });
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
        btnBackConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigActivity.this,ChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
                ConfigActivity.this.startActivity(intent, bundle);
                finishAffinity();
            }
        });
        btnLogoutConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(ConfigActivity.this)
                        .setTitle("Deseja efetuar o logout?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ConfigActivity.this, LoginActivity.class);
                                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
                                ConfigActivity.this.startActivity(intent, bundle);
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
        super.onBackPressed();
        Intent intent = new Intent(ConfigActivity.this,ChatActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
        ConfigActivity.this.startActivity(intent, bundle);
        finish();
    }
}