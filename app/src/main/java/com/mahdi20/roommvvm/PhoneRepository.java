package com.mahdi20.roommvvm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.mahdi20.roommvvm.phonetools.model.Phone;
import com.mahdi20.roommvvm.phonetools.model.PhoneDao;

import java.util.List;

public class PhoneRepository {
    private PhoneDao phoneDao;
    private LiveData<List<Phone>> allPhones;

    public PhoneRepository(Application application) {
        PhoneDatabase database = PhoneDatabase.getInstance(application);
        phoneDao = database.phoneDao();
        allPhones = phoneDao.getAllPhones();
    }

    public void insert(Phone phone) {
        new InsertPhoneAsyncTask(phoneDao).execute(phone);
    }

    public void update(Phone phone) {
        new UpdatePhoneAsyncTask(phoneDao).execute(phone);
    }

    public void delete(Phone phone) {
        new DeletePhoneAsyncTask(phoneDao).execute(phone);
    }

    public void deleteAllphones() {
        new DeleteAllPhonesAsyncTask(phoneDao).execute();
    }

    public LiveData<List<Phone>> getAllphones() {
        return allPhones;
    }

    private static class InsertPhoneAsyncTask extends AsyncTask<Phone, Void, Void> {
        private PhoneDao phoneDao;

        private InsertPhoneAsyncTask(PhoneDao phoneDao) {
            this.phoneDao = phoneDao;
        }

        @Override
        protected Void doInBackground(Phone... phones) {
            phoneDao.insert(phones[0]);
            return null;
        }
    }

    private static class UpdatePhoneAsyncTask extends AsyncTask<Phone, Void, Void> {
        private PhoneDao phoneDao;

        private UpdatePhoneAsyncTask(PhoneDao phoneDao) {
            this.phoneDao = phoneDao;
        }

        @Override
        protected Void doInBackground(Phone... phones) {
            phoneDao.update(phones[0]);
            return null;
        }
    }

    private static class DeletePhoneAsyncTask extends AsyncTask<Phone, Void, Void> {
        private PhoneDao phoneDao;

        private DeletePhoneAsyncTask(PhoneDao phoneDao) {
            this.phoneDao = phoneDao;
        }

        @Override
        protected Void doInBackground(Phone... phones) {
            phoneDao.delete(phones[0]);
            return null;
        }
    }

    private static class DeleteAllPhonesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PhoneDao phoneDao;

        private DeleteAllPhonesAsyncTask(PhoneDao phoneDao) {
            this.phoneDao = phoneDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            phoneDao.deleteAllPhones();
            return null;
        }
    }
}