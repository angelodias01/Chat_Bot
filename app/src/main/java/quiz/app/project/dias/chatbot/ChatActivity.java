package quiz.app.project.dias.chatbot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.ChatDao;
import quiz.app.project.dias.chatbot.Database.MessagesDao;
import quiz.app.project.dias.chatbot.Log_Reg_Activities.LoginActivity;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.ChatAdapterEventListener{

    private ChatAdapter adapter;
    Button btnConfig, btnChatLogout;
    FloatingActionButton btnAddMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ChatBot);
        setContentView(R.layout.chat_activity);

    // obter uma referência para a RecyclerView que existe no layout da MainActivity
    RecyclerView recyclerView = findViewById(R.id.recyclerViewChats);

    // obter uma instância do ChattDao e do Message
    AppDatabase db = AppDatabase.getInstance(this);
    ChatDao chatDao = db.getChatDao();
    MessagesDao messageDAO = db.getMessageDao();

    // criar um objeto do tipo ChatAdapter (que extende Adapter)
    this.adapter = new  ChatAdapter(chatDao.getAll(),messageDAO.getLastMessage(), this);
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
        List<Chat> newChatList = AppDatabase.getInstance(this).getChatDao().getAll();
        this.adapter.refreshList(newChatList);
    }

    @Override
    public void onChatClicked(int chatId) {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("chatIDKey", chatId);
        this.startActivity(intent);
    }

    @Override
    public void onContactLongClicked(int chatId) {
        ChatDao chatDao = AppDatabase.getInstance(ChatActivity.this).getChatDao();
        Chat chat = chatDao.getChatById(chatId);

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Delete chat");
        builder.setMessage("Do you really want to delete \"" + chat.getChatId() + "\" ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chatDao.delete(chat);
                List<Chat> newList = chatDao.getAll();
                adapter.refreshList(newList);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.btnConfig= findViewById(R.id.btnConfig);
        this.btnAddMsg = findViewById(R.id.btnAddMsg);
        this.btnChatLogout = findViewById(R.id.btnChatLogout);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, ConfigActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                ChatActivity.this.startActivity(intent, bundle);
            }
        });
        btnAddMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, NewChatActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(ChatActivity.this).toBundle();
                ChatActivity.this.startActivity(intent, bundle);
            }
        });
        btnChatLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(ChatActivity.this)
                        .setTitle("Deseja efetuar o logout?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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
        new AlertDialog.Builder(this)
                .setTitle("Deseja sair da aplicação?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
        finish();
    }
}