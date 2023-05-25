package quiz.app.project.dias.chatbot.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
//TODO
//need userid!!!!
@Entity(tableName = "UserMessages",foreignKeys = @ForeignKey(entity = Chat.class, parentColumns = "chatId", childColumns = "chatId"))
public class UserMessages {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userMessagesId")
    private int userMessageId;
    @ColumnInfo(name = "userMessage")
    private String userMessage;
    @ColumnInfo(name = "MessageTime")
    private String messageTime;
    @ColumnInfo(name = "chatId")
    private int chatId;

    public UserMessages(){}
    public UserMessages(int userMessageId, String userMessage, String messageTime, int chatId) {
        this.userMessageId = userMessageId;
        this.userMessage = userMessage;
        this.messageTime = messageTime;
        this.chatId = chatId;
    }

    public int getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(int userMessageId) {
        this.userMessageId = userMessageId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        messageTime = messageTime;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
