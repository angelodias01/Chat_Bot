package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "BotMessages",foreignKeys = @ForeignKey(entity = Bot.class, parentColumns = "botId", childColumns = "botId"))
public class BotMessages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botMessageId")
    private int botMessageId;
    @ColumnInfo(name = "receivedMessage")
    private String receivedMessage;
    @ColumnInfo(name = "botMessage")
    private String botMessageSent;
    @ColumnInfo(name = "botId")
    private int botId;
    public BotMessages() {
        // Empty constructor required by Room
    }

    public BotMessages( String receivedMessage, String botMessageSent, int botId) {
        //this.botMessageId = botMessageId;
        this.receivedMessage = receivedMessage;
        this.botMessageSent = botMessageSent;
        this.botId = botId;
    }

    public int getBotMessageId() {
        return botMessageId;
    }

    public void setBotMessageId(int botMessageId) {
        this.botMessageId = botMessageId;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void setReceivedMessage(String receivedMessage) {
        this.receivedMessage = receivedMessage;
    }

    public String getBotMessageSent() {
        return botMessageSent;
    }

    public void setBotMessageSent(String botMessageSent) {
        this.botMessageSent = botMessageSent;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }
}
