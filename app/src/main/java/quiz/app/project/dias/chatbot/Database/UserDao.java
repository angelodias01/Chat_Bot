package quiz.app.project.dias.chatbot.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    // Retrieve a user by username and password
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    // Retrieve all users from the "User" table
    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    // Retrieve a user's ID by username and password
    @Query("SELECT userId FROM User WHERE username = :username AND password = :password")
    int getUserById(String username, String password);

    // Retrieve a user by username
    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByEmail(String username);

    // Retrieve a user by password
    @Query("SELECT * FROM User WHERE password = :password")
    User getUserByPassword(String password);

    // Insert multiple users
    @Insert
    void insertAll(User... user);

    // Update a user
    @Update
    void updateAll(User user);

    // Delete all users
    @Query("DELETE FROM User")
    void deleteAll();
}
