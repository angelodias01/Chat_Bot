package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface BotDao {
        // Retrieve all bots from the database
        @Query("SELECT * FROM Bot")
        List<Bot> getAllBots();

        // Retrieve the botId for a given botName
        @Query("SELECT botId FROM Bot WHERE botName = :botName")
        int getBotById(String botName);
}
