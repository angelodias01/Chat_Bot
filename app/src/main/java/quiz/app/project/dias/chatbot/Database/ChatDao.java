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

    // Retreive the botId by chatId
    @Query("SELECT botId FROM chat WHERE chatId = :chatId")
    int getBotByChat(int chatId);


    // Retrieve chatId by userId
    @Query("SELECT chatId, userId, botId FROM chat WHERE userId = :userId")
    Chat getChatIdByUser(int userId);

    // Update last message date for a chat
    @Query("UPDATE chat SET lastMessageDate = :date WHERE chatID = :id ")
    void updateLastMessageDate(String date, int id);

    @Query("UPDATE chat SET lastMessage = :lastMessage WHERE chatID = :id ")
    void updateLastMessage(String lastMessage, int id);

    // Retrieve bot name by chatId
    @Query("SELECT botName FROM chat, bot, user WHERE chatId = :chatId AND chat.botId = bot.botId")
    String getBotNameByChatId(int chatId);
    @Query("DELETE FROM messages  WHERE messages.chatId IN (SELECT chat.chatId FROM chat WHERE chat.userId = :userId)")
    void deleteMsg(int userId);
    @Query("DELETE FROM chat WHERE chat.userId = :userId")
    void deleteChat(int userId);

    // Insert a new chat
    @Insert
    void insert(Chat chat);

    // Retrieve a chat by chatId
    @Query("SELECT * FROM chat WHERE chatId = :chatId")
    List<Chat> getChat(int chatId);

    // Delete a chat
    @Delete
    void delete(List<Chat> chat);
}
