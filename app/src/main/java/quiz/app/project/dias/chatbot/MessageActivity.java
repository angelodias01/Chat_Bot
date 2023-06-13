package quiz.app.project.dias.chatbot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Bot;
import quiz.app.project.dias.chatbot.Database.BotMessages;
import quiz.app.project.dias.chatbot.Database.BotMessagesDao;
import quiz.app.project.dias.chatbot.Database.ChatDao;
import quiz.app.project.dias.chatbot.Database.Messages;
import quiz.app.project.dias.chatbot.Database.MessagesDao;

public class MessageActivity extends AppCompatActivity {
    private MessagesAdapter adapter;
    private EditText tbMessage;
    private Button btnSend;
    private  LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private int chatID;
    private BotMessagesDao botMessagesDAO;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);

        tbMessage = findViewById(R.id.tbMessage);
        btnSend = findViewById(R.id.btnSend);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.chatID = bundle.getInt("chatId");

        // obter uma referência para a RecyclerView que existe no layout da chatActivity
        recyclerView = findViewById(R.id.recyclerViewMessages);

        // obter uma instância do messageDAO
        AppDatabase db = AppDatabase.getInstance(this);
        ChatDao chatDao = db.getChatDao();
        MessagesDao messagesDao = db.getMessageDao();
        botMessagesDAO = db.getBotMessagesDao();

        // criar um objeto do tipo MessageAdapter (que extende Adapter)
        this.adapter = new MessagesAdapter(messagesDao.getMessagesByChatId(chatID));


        // criar um objecto do tipo LinearLayoutManager para ser utilizado na RecyclerView
        // o LinearLayoutManager tem como orientação default a orientação Vertical
        this.layoutManager = new LinearLayoutManager(this);

        // Definir que a RecyclerView utiliza como Adapter o objeto que criámos anteriormente
        recyclerView.setAdapter(this.adapter);
        // Definir que a RecyclerView utiliza como LayoutManager o objeto que criámos anteriormente
        recyclerView.setLayoutManager(this.layoutManager);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int senderId = 0;

                String message = tbMessage.getText().toString();

                // Learned the method trim() in
                // https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces

                if (message.trim().length() > 0){

                    // Source: https://stackoverflow.com/questions/27016547/how-to-keep-recyclerview-always-scroll-bottom
                    layoutManager.setStackFromEnd(true);

                    tbMessage.setText("");

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

                    String hour = currentHour + ":" + minuteFormater(calendar);

                    currentDate += " " + hour;

                    Messages newMessage = new Messages(message,currentDate, senderId, chatID);

                    messagesDao.insert(newMessage);

                    db.getChatDao().updateLastMessageDate(currentDate,chatID);
                    db.getChatDao().updateLastMessage(message, chatID);

                    List<Messages> newMessageList = db.getMessageDao().getMessagesByChatId(chatID);
                    MessageActivity.this.adapter.refreshList(newMessageList, MessageActivity.this);

                    recyclerView.scrollToPosition(adapter.getItemCount()-1);

                    botAnswer(chatID, currentDate, message, messagesDao,botMessagesDAO, db);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        layoutManager.setStackFromEnd(true);
    }

    public static String minuteFormater(Calendar calendar){
        int currentMinute = calendar.get(Calendar.MINUTE);
        String minutes = Integer.toString(currentMinute);


        if(currentMinute < 10){
            minutes = "0" + currentMinute;
        }

        return minutes;
    }

    public void botAnswer(int chatId, String currentDate, String message, MessagesDao messageDAO, BotMessagesDao botMessagesDAO, AppDatabase db){
        int botSenderId = 1;

        Messages newMessage = new Messages(message, currentDate , botSenderId , chatId);

        int botIdinChat = AppDatabase.getInstance(this).getChatDao().getBotByChat(chatId);

        int countWordsSimillar = botMessagesDAO.getBotMessagesByWord(message, botIdinChat);

        if (countWordsSimillar > 0){
            Random random = new Random();
            int randomNum = random.nextInt(countWordsSimillar);

            BotMessagesDao botMessagesDao = db.getBotMessagesDao();
            List<BotMessages> botMessagesList = botMessagesDao.getBotMessagesByWordList(message, botIdinChat);

            String botMessages = botMessagesList.get(randomNum).getBotMessageSent();

            newMessage = new Messages(botMessages, currentDate , botSenderId , chatId);

            messageDAO.insert(newMessage);

            db.getChatDao().updateLastMessageDate(currentDate,chatId);
            db.getChatDao().updateLastMessage(botMessages, chatId);

            List<Messages> newMessageList = db.getMessageDao().getMessagesByChatId(chatId);
            MessageActivity.this.adapter.refreshList(newMessageList, MessageActivity.this);

            recyclerView.scrollToPosition(adapter.getItemCount()-1);

        }

    }
}
