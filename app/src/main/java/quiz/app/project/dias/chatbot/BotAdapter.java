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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import quiz.app.project.dias.chatbot.Database.Bot;
import quiz.app.project.dias.chatbot.Database.Messages;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.botViewHolder> {
    // variável de instância que armazena a lista de Contactos que este Adapter vai utilizar
    private List<Bot> botList;
    private Context context;

    /**
     * Construtor que recebe uma Lista de contactos a ser utilizada por este ContactAdapter
     */
    public BotAdapter(Context context) {
        // armazenar na variável de instância o valor do parâmetro do construtor
        this.botList = new ArrayList<>();
        this.context = context;
    }

    /**
     * Criar um novo ViewHolder sempre que for necessário
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public botViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Criar um objeto do tipo View com base no layout criado (contact_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbots, parent, false);
        // criar e devolver um objeto do tipo ContactViewHolder
        return new botViewHolder(rootView);
    }
    /**
     * Permite associar a um ViewHolder dados de um item da lista
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull botViewHolder holder, int position) {
        // obter o contact que existe na lista na posição dada pelo parâmetro position
        final Bot bot = this.botList.get(position);
        // definir que o valor da TextView no ViewHolder passa a conter o valor da propriedade name do Contact
        holder.lblBotName.setText(bot.getBotName());

        //click bot to message with bot and ok
        holder.tvSelectBot.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, Messages.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
            context.startActivity(intent, bundle);
        });
    }

    /**
     * Devolver o número de Items que a RecyclerView deve apresentar
     * @return numero de items da recyclerView
     */
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
        public botViewHolder(@NonNull View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.lblBotName = rootView.findViewById(R.id.lblBotName);
            this.tvSelectBot = rootView.findViewById(R.id.tvSelectBot);
        }
    }
}