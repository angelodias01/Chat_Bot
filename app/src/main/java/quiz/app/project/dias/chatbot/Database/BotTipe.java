package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "BotTipe", foreignKeys = @ForeignKey(entity = BotMessages.class, parentColumns = "botMessageId", childColumns = "botMessageID"))
public class BotTipe {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botTipeId")
    private int BotTipeId;
    @ColumnInfo(name = "botTipe")
    private String BotTipe;
    @ColumnInfo(name = "botMessageId")
    private int botMessageId;
    public BotTipe() {
    }
    public BotTipe(int botTipeId, String botTipe, int botMessageId) {
        this.BotTipeId = botTipeId;
        this.BotTipe = botTipe;
        this.botMessageId = botMessageId;
    }

    public int getBotTipeId() {
        return BotTipeId;
    }

    public void setBotTipeId(int botTipeId) {
        BotTipeId = botTipeId;
    }

    public String getBotTipe() {
        return BotTipe;
    }

    public void setBotTipe(String botTipe) {
        BotTipe = botTipe;
    }

    public int getBotMessageId(){
        return botMessageId;
    }

    public void setBotMessageId(int botMessageId) {
        botMessageId = botMessageId;
    }
}

