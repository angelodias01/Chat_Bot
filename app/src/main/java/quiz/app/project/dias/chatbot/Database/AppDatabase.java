package quiz.app.project.dias.chatbot.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Bot.class, Chat.class, Messages.class, BotMessages.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract UserDao getUserDao();
    public abstract BotDao getBotDao();
    public abstract ChatDao getChatDao();
    public abstract UserMessagesDao getUserMessageDao();
    public abstract BotMessagesDao getBotMessagesDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            //code not working!!!
                            db.execSQL("INSERT INTO User VALUES (1, 'admin', 'admin')");
                            db.execSQL("INSERT INTO Bot VALUES(1,'Bot Agressivo', 'Agressivo')");
                            db.execSQL("INSERT INTO Bot VALUES(2,'Bot Normal', 'Normal')");
                            db.execSQL("INSERT INTO Bot VALUES(3,'Bot Amigavel', 'Amigavel')");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}