package quiz.app.project.dias.chatbot;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quiz.app.project.dias.chatbot.Database.AppDatabase;
import quiz.app.project.dias.chatbot.Database.Messages;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{
    List<Messages> messageList;

    public MessagesAdapter(List<Messages> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        Messages message = messageList.get(position);

        if(message.getSenderId() == 0){
            return 0;
        }
        else{
            return 1;
        }
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
        else{
            // Criar um objeto do tipo View com base no layout criado (message_item.xml)
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message_item, parent, false);
            // criar e devolver um objeto do tipo ContactViewHolder
            return new MessagesAdapter.MessageReceivedViewHolder(rootView, parent.getContext());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessageViewHolder holder, int position) {

        if (getItemViewType(position) == 0){
            Messages message = messageList.get(position);
            holder.lblMessageSent.setText(message.getMessage());
            holder.lblDateSent.setText(message.getMessageTime());
        }else{
            Messages message = messageList.get(position);
            holder.lblMessageReceived.setText(message.getMessage());
            holder.lblDateReceived.setText(message.getMessageTime());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View rootView;
        private TextView lblMessageReceived, lblDateReceived;
        private TextView lblMessageSent, lblDateSent;
        
        private RecyclerView optionsRecyclerView;

        public MessageViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.lblMessageReceived = this.rootView.findViewById(R.id.lblMessageReceived);
            this.lblMessageSent = this.rootView.findViewById(R.id.lblMessageSent);
            this.lblDateSent = this.rootView.findViewById(R.id.lblDateSent);
            this.lblDateReceived = this.rootView.findViewById(R.id.lblDateReceived);
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

    public void refreshList(List<Messages> newMessageList, Context context) {
        this.messageList = newMessageList;
        notifyDataSetChanged();
    }
}

