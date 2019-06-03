package com.mahdi20.roommvvm;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.mahdi20.roommvvm.phonetools.model.Phone;
import com.mahdi20.roommvvm.phonetools.model.PhoneDao;


@Database(entities = {Phone.class}, version = 1)
public abstract class PhoneDatabase extends RoomDatabase {

    private static PhoneDatabase instance;

    public abstract PhoneDao phoneDao();

    public static synchronized PhoneDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PhoneDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PhoneDao noteDao;

        private PopulateDbAsyncTask(PhoneDatabase db) {
            noteDao = db.phoneDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Phone("Mahdi Asoodeh", "3337"));
            return null;
        }
    }
}