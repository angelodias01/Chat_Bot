package quiz.app.project.dias.chatbot;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Bot;
import quiz.app.project.dias.chatbot.Database.BotDao;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.ChatDao;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.botViewHolder> {
    //instance variable that stores the list of bot and the context that this adapter will use
    private List<Bot> botList;
    private Context context;

    public int userID;
    int botID;

    //constructor that receives a bot list and the context to be used by this bot adapter
    public BotAdapter(Context context, int userID) {
        //store in the instance variable the value of the constructor parameter
        this.botList = new ArrayList<>();
        this.context = context;
        this.userID = userID;
    }

    @NonNull
    @Override
    public botViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create a view object based on the layout created (bot_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbots, parent, false);
        //create and return an object of the bot view holder type
        return new botViewHolder(rootView, parent.getContext());
    }
    //allows to associate to a view holder data of a list item
    @Override
    public void onBindViewHolder(@NonNull botViewHolder holder, int position) {
        //get the bot that exists in the list at the position given by the position parameter
        final Bot bot = this.botList.get(position);
        //set that the value of the text view in the view holder now contains the value of the bots name property
        holder.lblBotName.setText(bot.getBotName());

        holder.tvSelectBot.setOnClickListener(view1 -> {
            String botName = holder.lblBotName.getText().toString();
            AppDatabase db = AppDatabase.getInstance(context);
            BotDao botDao = db.getBotDao();
            botID = botDao.getBotById(botName);
            Intent intent = new Intent(context, MessageActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) holder.context).toBundle();
            createChat(holder.context);
            holder.context.startActivity(intent, bundle);
        });
    }
    public void createChat(Context context){
        AppDatabase db = AppDatabase.getInstance(context);
        ChatDao chatDao = db.getChatDao();

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        String hour = currentHour + ":" + minuteFormater(calendar);
        Chat chat = new Chat(userID,botID,currentDate + " " + hour,"");

        chatDao.insert(chat);
    }

    public static String minuteFormater(Calendar calendar){
        int currentMinute = calendar.get(Calendar.MINUTE);
        String minutes = Integer.toString(currentMinute);


        if(currentMinute < 10){
            minutes = "0" + currentMinute;
        }

        return minutes;
    }

    //return the number of Items that the recycler view should display
    @Override
    public int getItemCount() {
        return this.botList.size();
    }

    public void refreshList(List<Bot> newBotList) {
        this.botList = newBotList;
        notifyDataSetChanged();
    }

    public class botViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView lblBotName, tvSelectBot;
        private Context context;
        public botViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.rootView = rootView;
            this.context = context;
            this.lblBotName = rootView.findViewById(R.id.lblBotName);
            this.tvSelectBot = rootView.findViewById(R.id.tvSelectBot);
        }
    }
}