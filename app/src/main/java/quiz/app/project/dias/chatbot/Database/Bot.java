package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bot")
public class Bot {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botId")
    private int botId;
    @ColumnInfo(name = "botName")
    private String botName;
    @ColumnInfo(name = "botTipe")
    private String botTipe;

    public Bot() {
        // Empty constructor
    }

    public Bot(int botId, String botName, String botTipe) {
        this.botId = botId;
        this.botName = botName;
        this.botTipe = botTipe;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotTipe() {
        return botTipe;
    }

    public void setBotTipe(String botTipe) {
        this.botTipe = botTipe;
    }
}

