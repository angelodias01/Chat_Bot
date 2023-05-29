package quiz.app.project.dias.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import quiz.app.project.dias.chatbot.Database.Chat;
import quiz.app.project.dias.chatbot.Database.Messages;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    private List<Chat> chatList;
    private List<Messages> messagesList;
    private ChatAdapterEventListener eventListener;

    public ChatAdapter(List<Chat> chatList, List<Messages> messageList, ChatAdapterEventListener eventListener) {
        // armazenar na variável de instância o valor do parâmetro do construtor
        this.chatList = chatList;
        this.messagesList = messagesList;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Criar um objeto do tipo View com base no layout criado (chat_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        // criar e devolver um objeto do tipo ContactViewHolder
        return new ChatViewHolder(rootView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        final Chat chat = this.chatList.get(position);

        holder.lblLastDateChat.setText(chat.getLastMessageDate());
        holder.lblLastMsgChat.setText(chat.getLastMessage());

        if (chat.getLastMessage() != null) {
            holder.lblLastDateChat.setText(chat.getLastMessage());
        } else {
            holder.lblLastDateChat.setText("");
        }

        holder.ConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventListener != null) eventListener.onChatClicked(chat.getChatId());
            }
        });

        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (eventListener != null) {
                    eventListener.onContactLongClicked(chat.getChatId());
                    return true;
                } else {
                    return false;
                }
            }
        });

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
        void onContactLongClicked(int chatId);
    }

    public void refreshList(List<Chat> newChatList) {
        this.chatList = newChatList;
        notifyDataSetChanged();
    }
}
