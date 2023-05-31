package quiz.app.project.dias.chatbot;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Bot;
import quiz.app.project.dias.chatbot.Database.BotDao;
import quiz.app.project.dias.chatbot.Database.Chat;

public class NewChatActivity extends AppCompatActivity {
    private Button btnBack;
    private BotAdapter adapter;
    private static final String userId = "userId";
    public int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.new_chat_activity);

        // Retrieve the user ID from the intent extras
        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getInt(this.userId, 0);

        // Obtain a reference to the RecyclerView in the activity layout
        RecyclerView recyclerView = findViewById(R.id.recyclerViewBots);

        // Obtain an instance of BotDao from the app database
        AppDatabase db = AppDatabase.getInstance(this);
        BotDao botDao = db.getBotDao();

        // Create an instance of BotAdapter and pass the application context and user ID
        this.adapter = new BotAdapter(this.getApplicationContext(), userID);

        // Create an instance of LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Set the adapter and layout manager to the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Retrieve the latest list of bots from the database and update the adapter
        List<Bot> newBotList = AppDatabase.getInstance(this).getBotDao().getAllBots();
        this.adapter.refreshList(newBotList);

        // Log the number of bots
        Log.i("NewChatActivity", "Number of bots: " + newBotList.size());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Set the click listener for the back button
        this.btnBack = findViewById(R.id.btnBackConfig);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the ChatActivity when the button is clicked
                Intent intent = new Intent(NewChatActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(NewChatActivity.this).toBundle();
                finish();
                startActivity(intent, bundle);
            }
        });
    }
}