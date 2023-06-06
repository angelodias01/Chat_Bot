package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.ChatDao;
import quiz.app.project.dias.chatbot.Database.MessagesDao;
import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.ChatAdapterEventListener {
    private static final String TAG = "ChatActivity"; // Tag for logging

    // Constant for the key used to pass the userId to the activity
    private static final String userId = "userId";

    // User and chat identifiers
    public int userID;
    public int chatId;

    // Adapter for the chat RecyclerView
    private ChatAdapter adapter;

    // UI elements
    TextView lblBotNameChat;
    Button btnConfig, btnChatLogout;
    FloatingActionButton btnAddMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        // Set the theme for the activity
        setTheme(R.style.Theme_ChatBot);

        // Set the layout for the activity
        setContentView(R.layout.chat_activity);

        // Retrieve the userId from the extras passed to the activity
        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getInt(this.userId, 0);

        // Get a reference to the RecyclerView in the layout
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChats);

        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        AppDatabase db = AppDatabase.getInstance(this);
        ChatDao chatDao = db.getChatDao();
        MessagesDao messageDAO = db.getMessageDao();

        // Create an instance of the ChatAdapter and pass the necessary data
        this.adapter = new  ChatAdapter(chatDao.getChatById(userID), messageDAO.getLastMessage(), this, this.getApplicationContext());

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        // Retrieve the updated chat list from the database
        List<Chat> newChatList = AppDatabase.getInstance(this).getChatDao().getChatById(userID);

        // Refresh the adapter with the new chat list
        this.adapter.refreshList(newChatList);
    }

    @Override
    public void onChatClicked(int chatId) {
        Log.i(TAG, "onChatClicked");

        // Start the MessageActivity with the selected chat ID
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("chatIDKey", chatId);
        this.startActivity(intent);
    }

    @Override
    public void onChatLongClicked(int chatId) {
        Log.i(TAG, "onContactLongClicked");

        // Get an instance of ChatDao and retrieve the chat and bot name
        ChatDao chatDao = AppDatabase.getInstance(ChatActivity.this).getChatDao();
        String botname = chatDao.getBotNameByChatId(chatId);

        // Create an AlertDialog to confirm the deletion of the chat
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete chat");
        builder.setMessage("Do you really want to delete your chat with \"" + botname + "\" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Chat deleted");

                // Delete the chat from the database
                chatDao.delete(chatDao.getChat(chatId));

                // Get the updated chat list and refresh the adapter
                List<Chat> newList = chatDao.getChatById(userID);
                adapter.refreshList(newList);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Delete cancelled");

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

        // Get instances of ChatDao and UI elements
        AppDatabase db = AppDatabase.getInstance(ChatActivity.this);
        ChatDao chatDao = db.getChatDao();

        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getInt(this.userId, 0);
        this.btnConfig = findViewById(R.id.btnConfig);
        this.btnAddMsg = findViewById(R.id.btnAddMsg);
        this.btnChatLogout = findViewById(R.id.btnChatLogout);
        this.lblBotNameChat = findViewById(R.id.lblBotNameChat);

        // Set click listeners for the configuration, add message, and logout buttons
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Config button clicked");

                // Start the ConfigActivity and pass the chatId as an extra
                Intent intent = new Intent(ChatActivity.this, ConfigActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                intent.putExtra("userId", userID);
                ChatActivity.this.startActivity(intent, bundle);
            }
        });

        btnAddMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Add message button clicked");

                // Start the NewChatActivity and pass the userId as an extra
                Intent intent = new Intent(ChatActivity.this, NewChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                intent.putExtra("userId", userID);
                ChatActivity.this.startActivity(intent, bundle);
            }
        });

        btnChatLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Logout button clicked");

                // Show an AlertDialog to confirm the logout
                new AlertDialog.Builder(ChatActivity.this)
                        .setTitle("Deseja efetuar o logout?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "Logout confirmed");

                                // Start the LoginActivity and finish the current activity
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
        Log.i(TAG, "onBackPressed");

        // Show an AlertDialog to confirm exiting the application
        new AlertDialog.Builder(this)
                .setTitle("Deseja sair da aplicação?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Application exited");

                        // Finish the activity and terminate the application
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
        Log.i(TAG, "onDestroy");

        // Finish the activity
        finish();
    }
}