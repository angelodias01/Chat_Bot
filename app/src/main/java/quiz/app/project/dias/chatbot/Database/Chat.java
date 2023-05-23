package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chat",foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"), @ForeignKey(entity = Bot.class, parentColumns = "botId", childColumns = "botId")})
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chatId")
    private int chatId;
    @ColumnInfo(name = "chatName")
    private String chatName;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "botId")
    private int botId;

    public Chat(int chatId, String chatName, int userId, int botId) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.userId = userId;
        this.botId = botId;
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

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }
}
