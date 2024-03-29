package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chat", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Bot.class, parentColumns = "botId", childColumns = "botId",
                onDelete = ForeignKey.CASCADE)})
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chatId")
    private int chatId;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "botId")
    private int botId;
    @ColumnInfo(name = "lastMessageDate")
    public String lastMessageDate;
    @ColumnInfo(name = "lastMessage")
    private String lastMessage;

    public Chat(int userId, int botId, String lastMessageDate, String lastMessage) {
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
