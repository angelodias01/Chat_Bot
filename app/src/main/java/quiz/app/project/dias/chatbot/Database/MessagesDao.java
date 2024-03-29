package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MessagesDao {
    // Retrieve all messages from the "Messages" table
    @Query("SELECT * FROM Messages")
    List<Messages> getAll();

    // Retrieve the last message from a chat based on the senderId
    @Query("SELECT * FROM Messages WHERE senderId = :id ORDER BY messageTime ASC LIMIT 1")
    Messages getLastMessageFromChat(int id);

    // Retrieve the last messages from all chats
    @Query("SELECT * FROM Messages ORDER BY messageTime ASC")
    List<Messages> getLastMessage();

    @Query("Delete from Messages where chatId = :chatId")
    void deleteFromChatId(int chatId);

    // Retrieve messages by chatId
    @Query("SELECT * FROM Messages WHERE chatId = :chatId ORDER BY messageTime DESC")
    List<Messages> getMessagesByChatId(int chatId);

    // Insert a new message
    @Insert
    void insert(Messages messages);

    // Delete a message
    @Delete
    void delete(Messages messages);
}
