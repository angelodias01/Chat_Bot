package quiz.app.project.dias.chatbot;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{
    List<Message> messageList;

    public MessagesAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        return message.getSender() == 0 ? 0 : 1;
    }

    @NonNull
    @Override
    public MessagesAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            // Criar um objeto do tipo View com base no layout criado (message_item.xml)
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_item, parent, false);
            // criar e devolver um objeto do tipo ContactViewHolder
            return new MessagesAdapter.MessageSentViewHolder(rootView, parent.getContext());
        }
        else if(viewType == 1) {
            // Criar um objeto do tipo View com base no layout criado (message_item.xml)
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message_item, parent, false);
            // criar e devolver um objeto do tipo ContactViewHolder
            return new MessagesAdapter.MessageReceivedViewHolder(rootView, parent.getContext());
        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessageViewHolder holder, int position) {

        if(getItemViewType(position) == 1 || getItemViewType(position) == 0){
            AppDatabase db = AppDatabase.getInstance(holder.context);

            final Message messageList = this.messageList.get(position);

            String chatName = db.getChatDao().getBotNameByChatId(messageList);

            if(messageList.senderId == 0){
                chatName = "You";
            }

            holder.lblLastDateChat.setText(messageList.getDate());
            holder.lblBotName.setText(chatName);
            holder.lblLastDateChat.setText(messageList.getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View rootView;
        private TextView lblBotName;
        private TextView lblLastDateChat;
        private TextView lblLastMsgChat;

        private RecyclerView optionsRecyclerView;

        public MessageViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.lblBotName = this.rootView.findViewById(R.id.lblBotName);
            this.lblLastDateChat = this.rootView.findViewById(R.id.lblLastDateChat);
            this.lblLastMsgChat = this.rootView.findViewById(R.id.lblLastMsgChat);
        }
    }

    public class MessageReceivedViewHolder extends MessageViewHolder {
        public MessageReceivedViewHolder(@NonNull View rootView, Context context) {
            super(rootView, context);
        }
    }

    public class MessageSentViewHolder extends MessageViewHolder {
        public MessageSentViewHolder(@NonNull View rootView, Context context) {
            super(rootView, context);
        }
    }

    public class ExerciseViewHolder extends  MessageViewHolder{
        public ExerciseViewHolder(@NonNull View rootView, Context context){
            super(rootView, context);
        }
    }

    public void refreshList(List<Message> newMessageList, Context context) {

        this.messageList = newMessageList;
        notifyDataSetChanged();
    }
}

