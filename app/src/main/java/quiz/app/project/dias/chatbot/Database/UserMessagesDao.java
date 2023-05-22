package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserMessagesDao {
    @Query("Select * from UserMessages")
    List<UserMessages> getAllMessages();
}
