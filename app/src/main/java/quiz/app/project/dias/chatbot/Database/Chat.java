package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chat",foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"), @ForeignKey(entity = Bot.class, parentColumns = "botId", childColumns = "botId")})
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ChatId")
    private int chatId;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "botId")
    private int botId;
    public String lastMessageDate;
    private String lastMessage;

    public Chat(int chatId, int userId, int botId, String lastMessageDate, String lastMessage) {
        this.chatId = chatId;
        this.userId = userId;
        this.botId = botId;
        this.lastMessageDate = lastMessageDate;
        this.lastMessage = lastMessage;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
