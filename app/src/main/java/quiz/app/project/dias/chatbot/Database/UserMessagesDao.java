package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserMessagesDao {
    @Query("SELECT * FROM UserMessages WHERE userId = :id")
    List<Messages> getAll(int id);

    @Query("SELECT * FROM UserMessages WHERE userId = :id ORDER BY messageTime desc limit 1")
    Messages getLastMessageFromChat(int id);

    @Query("SELECT * FROM UserMessages ORDER BY messageTime DESC ")
    List<Messages> getLastMessage();

    @Insert
    void insert(Messages message);
}
