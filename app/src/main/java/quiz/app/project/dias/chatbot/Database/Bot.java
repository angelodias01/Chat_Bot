package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bot", foreignKeys = {@ForeignKey(entity = BotTipe.class, parentColumns = "botTipeId", childColumns = "botTipeId")})
public class Bot {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botId")
    private int BotId;
    @ColumnInfo(name = "botName")
    private String BotName;
    @ColumnInfo(name = "botTipeId")
    private int botTipeId;

    public Bot() {
        // Empty constructor
    }

    public Bot(int botId, String botName, int botTipeId) {
        this.BotId = botId;
        this.BotName = botName;
        this.botTipeId = botTipeId;
    }

    public int getBotId() {
        return BotId;
    }

    public void setBotId(int botId) {
        BotId = botId;
    }

    public String getBotName() {
        return BotName;
    }

    public void setBotName(String botName) {
        BotName = botName;
    }

    public int getBotTipeId() {
        return botTipeId;
    }

    public void setBotTipeId(int botTipeId) {
        this.botTipeId = botTipeId;
    }
}

