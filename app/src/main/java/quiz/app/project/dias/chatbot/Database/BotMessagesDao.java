package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BotMessagesDao {

    @Query("SELECT count(*) FROM botMessages WHERE receivedMessage LIKE :word AND botId = :botId")
    int getBotMessagesByWord(String word, int botId);

    @Query("SELECT * FROM botMessages WHERE receivedMessage LIKE :word AND botId = :botId")
    List<BotMessages> getBotMessagesByWordList(String word, int botId);
}
