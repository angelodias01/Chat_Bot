package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Messages",foreignKeys = @ForeignKey(entity = Chat.class, parentColumns = "chatId", childColumns = "chatId",
        onDelete = ForeignKey.CASCADE))
public class Messages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messagesId")
    private int messageId;
    @ColumnInfo(name = "Message")
    private String Message;
    @ColumnInfo(name = "MessageTime")
    private String messageTime;
    @ColumnInfo(name = "senderId")
    private int senderId;
    @ColumnInfo (name = "chatId")
    private int chatId;
    public Messages(){
        // Empty constructor required by Room
    }

    public Messages(String Message, String messageTime, int senderId,int chatId) {
        this.Message = Message;
        this.messageTime = messageTime;
        this.senderId = senderId;
        this.chatId = chatId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int MessageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        messageTime = messageTime;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int userId) {
        this.senderId = senderId;
    }
    public int getChatId() {
        return chatId;
    }
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
