package quiz.app.project.dias.chatbot.Database;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Bot.class, Chat.class, Messages.class, BotMessages.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase"; // Tag for logging
    private static AppDatabase INSTANCE;

    // Declare DAO interfaces
    public abstract UserDao getUserDao();
    public abstract BotDao getBotDao();
    public abstract ChatDao getChatDao();
    public abstract MessagesDao getMessageDao();
    public abstract BotMessagesDao getBotMessagesDao();

    // Get an instance of the AppDatabase
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.i(TAG, "onCreate: Initializing database");

                            // Insert initial data into tables upon database creation
                            db.execSQL("INSERT INTO User VALUES (1, 'admin', 'admin')");
                            db.execSQL("INSERT INTO Bot VALUES(1,'Bot Agressivo', 'Agressivo')," +
                                    "(2,'Bot Normal', 'Normal'),(3,'Bot Amigavel', 'Amigavel')");
                            db.execSQL("insert into botmessages values('Olá', 'O que queres?',1),('Olá', 'Alõ',2),('Olá', 'Olá, tudo bem?',3)");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
