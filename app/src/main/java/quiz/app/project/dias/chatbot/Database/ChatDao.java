package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ChatDao {


    @Query("SELECT * FROM chat ORDER BY lastMessageDate DESC")
    List<Chat> getAll();


    @Query("SELECT * FROM chat WHERE chatID = :chatId")
    Chat getChatById(int chatId);


    @Query("UPDATE chat SET lastMessageDate = :date WHERE chatID = :id ")
    void updateLastMessageDate(String date, int id);

    @Insert
    void insert(Chat chat);

    @Delete
    void delete(Chat chat);

}
