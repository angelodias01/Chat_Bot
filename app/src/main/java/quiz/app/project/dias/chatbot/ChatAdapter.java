package quiz.app.project.dias.chatbot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.ChatDao;
import quiz.app.project.dias.chatbot.Database.Messages;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private static final String TAG = "ChatAdapter"; // Log tag for debugging

    private List<Chat> chatList;
    private Context context;
    private List<Messages> messagesList;
    private ChatAdapterEventListener eventListener;

    public ChatAdapter(List<Chat> chatList, List<Messages> messageList, ChatAdapterEventListener eventListener, Context context) {
        // Store in the instance variables the values of the constructor parameters
        this.chatList = chatList;
        this.messagesList = messagesList;
        this.eventListener = eventListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a view object based on the layout created (chat_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        // Create and return an object of the ChatViewHolder type
        return new ChatViewHolder(rootView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        final Chat chat = this.chatList.get(position);
        holder.context = this.context;
        AppDatabase db = AppDatabase.getInstance(context);
        ChatDao chatDao = db.getChatDao();

        // Bind data to the views in the view holder
        holder.lblLastDateChat.setText(chat.getLastMessageDate());
        holder.lblLastMsgChat.setText(chat.getLastMessage());
        holder.lblBotNameChat.setText(chatDao.getBotNameByChatId(chat.getChatId()));

        // Check if the last message is null and update the text accordingly
        if (chat.getLastMessage() != null) {
            holder.lblLastDateChat.setText(chat.getLastMessage());
        } else {
            holder.lblLastDateChat.setText("");
        }

        // Set click listener for the chat item
        holder.ConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListener != null) {
                    eventListener.onChatClicked(chat.getChatId());
                }
            }
        });

        // Set long click listener for the chat item
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (eventListener != null) {
                    eventListener.onChatLongClicked(chat.getChatId());
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Log the bound chat item for debugging
        Log.i(TAG, "Bound chat item at position " + position + ": " + chat.toString());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private View rootView;
        private TextView lblBotNameChat;
        private TextView lblLastMsgChat;
        private TextView lblLastDateChat;
        public ConstraintLayout ConstraintLayout;

        public ChatViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.lblBotNameChat = rootView.findViewById(R.id.lblBotNameChat);
            this.lblLastMsgChat = rootView.findViewById(R.id.lblLastMsgChat);
            this.lblLastDateChat = rootView.findViewById(R.id.lblLastDateChat);
            this.ConstraintLayout = rootView.findViewById(R.id.ConstraintLayout);
        }
    }

    public interface ChatAdapterEventListener {
        void onChatClicked(int chatId);
        void onChatLongClicked(int chatId);
    }

    public void refreshList(List<Chat> newChatList) {
        this.chatList = newChatList;
        notifyDataSetChanged();
    }
}