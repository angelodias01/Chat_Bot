package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Messages")
public class Messages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "MessagesId")
    private int MessageId;
    @ColumnInfo(name = "Message")
    private String Message;
    @ColumnInfo(name = "MessageTime")
    private String messageTime;
    @ColumnInfo(name = "senderId")
    private int senderId;

    public Messages(){}

    public Messages(int MessageId, String Message, String messageTime, int senderId) {
        this.MessageId = MessageId;
        this.Message = Message;
        this.messageTime = messageTime;
        this.senderId = senderId;
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int MessageId) {
        this.MessageId = MessageId;
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
}
