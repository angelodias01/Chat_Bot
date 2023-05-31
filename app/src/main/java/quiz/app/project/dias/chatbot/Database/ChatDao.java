package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ChatDao {
    // Retrieve all chats ordered by last message date in descending order
    @Query("SELECT * FROM chat ORDER BY lastMessageDate DESC")
    List<Chat> getAll();

    // Retrieve chats by userId
    @Query("SELECT * FROM chat WHERE userId = :userId")
    List<Chat> getChatById(int userId);

    // Retrieve chatId by userId
    @Query("SELECT chatId FROM chat WHERE userId = :userId")
    Chat getChatIdByUser(int userId);

    // Update last message date for a chat
    @Query("UPDATE chat SET lastMessageDate = :date WHERE chatID = :id")
    void updateLastMessageDate(String date, int id);

    // Retrieve bot name by chatId
    @Query("SELECT botName FROM chat, bot, user WHERE chatId = :chatId AND chat.botId = bot.botId")
    String getBotNameByChatId(int chatId);

    // Insert a new chat
    @Insert
    void insert(Chat chat);

    // Retrieve a chat by chatId
    @Query("SELECT * FROM chat WHERE chatId = :chatId")
    Chat getChat(int chatId);

    // Delete a chat
    @Delete
    void delete(Chat chat);
}
