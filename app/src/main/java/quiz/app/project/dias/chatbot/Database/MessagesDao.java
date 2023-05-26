package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessagesDao {
    @Query("SELECT * FROM Messages")
    List<Messages> getAll();

    @Query("SELECT * FROM Messages WHERE senderId = :id ORDER BY messageTime desc limit 1")
    Messages getLastMessageFromChat(int id);

    @Query("SELECT * FROM Messages ORDER BY messageTime DESC ")
    List<Messages> getLastMessage();

    @Insert
    void insert(Messages messages);
}
