package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ChatDao {


    @Query("SELECT * FROM chat ORDER BY lastMessageDate DESC")
    List<Chat> getAll();


    @Query("SELECT * FROM chat WHERE userId = :userId")
    List<Chat> getChatById(int userId);
    @Query("SELECT chatId FROM chat WHERE userId = :userId")
    Chat getChatIdByUser(int userId);


    @Query("UPDATE chat SET lastMessageDate = :date WHERE chatID = :id ")
    void updateLastMessageDate(String date, int id);
    @Query("SELECT botName FROM chat, bot, user WHERE chatId = :chatId AND chat.botId = bot.botId")
    String getBotNameByChatId(int chatId);
    @Insert
    void insert(Chat chat);

    @Query("select * from chat where chatId = :chatId")
    Chat getChat(int chatId);
    @Delete
    void delete(Chat chat);

}
