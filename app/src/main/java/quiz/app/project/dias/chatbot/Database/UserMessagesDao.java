package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserMessagesDao {
    @Query("SELECT * FROM UserMessages WHERE chatId = :id")
    List<UserMessages> getAll(int id);

    @Query("SELECT * FROM UserMessages WHERE chatId = :id ORDER BY messageTime desc limit 1")
    UserMessages getLastMessageFromChat(int id);

    @Query("SELECT * FROM UserMessages ORDER BY messageTime DESC ")
    List<UserMessages> getLastMessage();

    @Insert
    void insert(UserMessages message);
}
