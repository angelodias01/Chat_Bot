package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        // Retrieve the user ID from the intent extras
        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getInt(this.userId, 0);
        Log.i("ConfigActivity", "User ID: " + userID);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Initialize buttons
        this.btnLimpar = findViewById(R.id.btnLimpar);
        this.btnBackConfig = findViewById(R.id.btnBackConfig);
        this.btnLogoutConfig = findViewById(R.id.btnLogoutConfig);

        // Retrieve the user ID from the intent extras
        Bundle bundle = getIntent().getExtras();
        // Set click listener for "Limpar" button
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create database instances
                AppDatabase db = Room.databaseBuilder(ConfigActivity.this, AppDatabase.class, "AppDatabase").build();
                UserDao userDao = db.getUserDao();
                ChatDao chatDao = db.getChatDao();
                MessagesDao messagesDao = db.getMessageDao();

                // Show confirmation dialog for data clearing
                new AlertDialog.Builder(ConfigActivity.this)
                        .setTitle("Deseja limpar os dados da aplicação?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                executor.execute(() -> {

                                    chatDao.deleteChat(userID);


                                    Log.i("ConfigActivity", "User ID: " + userID);
                                    Log.i("ConfigActivity", "Messages and Chat deleted for User ID: " + userID);

                                    List chatList = chatDao.getChatById(userID);
                                    //List messageList = messagesDao.getMessagesByChatId(chatDao.getChatById(userID));
                                    Log.i("ConfigActivity", "Chat List size: " + chatList.size());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (chatList != null) {
                                                // Show success message and navigate to MainScreenActivity
                                                Toast.makeText(ConfigActivity.this, "Dados limpos com sucesso!", Toast.LENGTH_SHORT).show();
                                                Log.i("ConfigActivity", "Data cleared successfully");
                                                Intent intent = new Intent(ConfigActivity.this, ChatActivity.class);
                                                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
                                                intent.putExtra("userId", userID);
                                                ConfigActivity.this.startActivity(intent, bundle);
                                                finishAffinity();
                                            } else {
                                                // Show error message if unable to clear data
                                                Toast.makeText(ConfigActivity.this, "Não foi possível limpar os dados!", Toast.LENGTH_SHORT).show();
                                                Log.i("ConfigActivity", "Failed to clear data");
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

        // Set click listener for "Back" button
        btnBackConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to ChatActivity
                Intent intent = new Intent(ConfigActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
                ConfigActivity.this.startActivity(intent, bundle);
                finishAffinity();
            }
        });

        // Set click listener for "Logout" button
        btnLogoutConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Show confirmation dialog for logout
                new AlertDialog.Builder(ConfigActivity.this)
                        .setTitle("Deseja efetuar o logout?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Navigate to LoginActivity
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
        // Navigate to ChatActivity
        Intent intent = new Intent(ConfigActivity.this, ChatActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ConfigActivity.this).toBundle();
        ConfigActivity.this.startActivity(intent, bundle);
        finish();
    }
}
