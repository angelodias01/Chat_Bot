package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("Select * from User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE userId = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByEmail(String username);

    @Query("SELECT * FROM User WHERE password = :password")
    User getUserByPassword(String password);

    @Insert
    void insertAll(User... user);
    @Update
    void updateAll(User user);
    @Delete
    void deleteAll(User user);
}
