package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ChatDao {

    @Query("Select * from Chat")
    List<Chat> getAllChats();

}
