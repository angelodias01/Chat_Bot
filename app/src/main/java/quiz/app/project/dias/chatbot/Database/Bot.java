package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bot")
public class Bot {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botId")
    private int BotId;
    @ColumnInfo(name = "botName")
    private String BotName;

    public Bot(int BotId, String BotName) {
        this.BotId = BotId;
        this.BotName = BotName;
    }

    public int getBotId() {
        return BotId;
    }

    public void setBotId(int BotId) {
        this.BotId = BotId;
    }

    public String getBotName() {
        return BotName;
    }

    public void setBotName(String BotName) {
        this.BotName = BotName;
    }
}

