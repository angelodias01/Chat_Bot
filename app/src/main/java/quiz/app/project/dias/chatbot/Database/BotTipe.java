package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "BotTipe", foreignKeys = @ForeignKey(entity = BotMessages.class, parentColumns = "botMessageId", childColumns = "botMessageId"))
public class BotTipe {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "botTipeId")
    private int BotTipeId;
    @ColumnInfo(name = "botTipe")
    private String BotTipe;
}

