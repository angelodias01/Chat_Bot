package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "BotMessages",foreignKeys = {@ForeignKey(entity=Chat.class, parentColumns="chatId", childColumns="chatId"), @ForeignKey(entity = BotTipe.class, parentColumns = "botTipeId", childColumns = "botTipeId")})
public class BotMessages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botMessageId")
    private int BotMessageId;
    @ColumnInfo(name = "botMessage")
    private String BotMessage;
    @ColumnInfo(name = "botMessageTime")
    private String botMessageTime;
    @ColumnInfo(name = "botTipeId")
    private int botTipeId;
    public BotMessages() {}
    public BotMessages(int botMessageId, String botMessage, String botMessageTime, int botTipeId) {
        this.BotMessageId = botMessageId;
        this.BotMessage = botMessage;
        this.botMessageTime = botMessageTime;
        this.botTipeId = botTipeId;
    }

    public int getbotTipeId() {
        return botTipeId;
    }

    public void setbotTipeId(int botTipeId) {
        this.botTipeId = botTipeId;
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
