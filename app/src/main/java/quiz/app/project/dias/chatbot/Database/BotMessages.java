package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "BotMessages")
public class BotMessages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botMessageId")
    private int BotMessageId;
    @ColumnInfo(name = "botMessage")
    private String BotMessage;
    @ColumnInfo(name = "botMessageTime")
    private String botMessageTime;
    public BotMessages() {}
    public BotMessages(int botMessageId, String botMessage, String botMessageTime) {
        this.BotMessageId = botMessageId;
        this.BotMessage = botMessage;
        this.botMessageTime = botMessageTime;
    }

    public int getBotMessageId() {
        return BotMessageId;
    }

    public void setBotMessageId(int botMessageId) {
        BotMessageId = botMessageId;
    }

    public String getBotMessage() {
        return BotMessage;
    }

    public void setBotMessage(String botMessage) {
        BotMessage = botMessage;
    }

    public String getBotMessageTime() {
        return botMessageTime;
    }

    public void setBotMessageTime(String botMessageTime) {
        this.botMessageTime = botMessageTime;
    }
}
