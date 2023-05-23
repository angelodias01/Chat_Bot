package quiz.app.project.dias.chatbot.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Bot.class, Chat.class, UserMessages.class, BotMessages.class, BotTipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract UserDao getUserDao();
    public abstract BotDao getBotDao();
    public abstract ChatDao getChatDao();
    public abstract UserMessagesDao getUserMessageDao();
    public abstract BotMessagesDao getBotMessagesDao();
    public abstract BotTipeDao getBotTipe();


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
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}