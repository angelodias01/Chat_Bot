package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserMessages",foreignKeys = @ForeignKey(entity=User.class, parentColumns="userId", childColumns="userId"))
public class UserMessages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chatId")
    private int chatId;
    @ColumnInfo(name = "chatName")
    private String chatName;
    @ColumnInfo(name = "userId")
    private int userId;

    public UserMessages(int chatId, String chatName, int userId) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
