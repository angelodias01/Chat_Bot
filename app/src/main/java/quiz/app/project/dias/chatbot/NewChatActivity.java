package quiz.app.project.dias.chatbot;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.new_chat_activity);

        // obter uma referência para a RecyclerView que existe no layout da MainActivity
        RecyclerView recyclerView = findViewById(R.id.recyclerViewBots);

        // obter uma instância do ContactDao
        AppDatabase db = AppDatabase.getInstance(this);
        BotDao botDao = db.getBotDao();

        // criar um objeto do tipo ContactAdapter (que extende Adapter)
        // ContactAdapter adapter = new ContactAdapter(MemoryDatabase.getAllContacts());
        this.adapter = new BotAdapter();
        // ContactAdapter adapter = new ContactAdapter(AppDatabase.getInstance(this).getContactDao().getAll());

        // criar um objecto do tipo LinearLayoutManager para ser utilizado na RecyclerView
        // o LinearLayoutManager tem como orientação default a orientação Vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Definir que a RecyclerView utiliza como Adapter o objeto que criámos anteriormente
        recyclerView.setAdapter(this.adapter);
        // Definir que a RecyclerView utiliza como LayoutManager o objeto que criámos anteriormente
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
        List<Bot> newBotList = AppDatabase.getInstance(this).getBotDao().getAllBots();
        this.adapter.refreshList(newBotList);
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
