package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface BotDao {
        @Query("Select * from Bot")
        List<Bot> getAllBots();
        @Query("Select botId from Bot where botName= :botName")
        int getBotById(String botName);
}
