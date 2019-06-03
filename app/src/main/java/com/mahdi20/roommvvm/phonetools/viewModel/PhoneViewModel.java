package com.mahdi20.roommvvm.phonetools.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mahdi20.roommvvm.PhoneRepository;
import com.mahdi20.roommvvm.phonetools.model.Phone;

import java.util.List;


public class PhoneViewModel extends AndroidViewModel {
    private PhoneRepository repository;
    private LiveData<List<Phone>> allphones;

    public PhoneViewModel(@NonNull Application application) {
        super(application);
        repository = new PhoneRepository(application);
        allphones = repository.getAllphones();
    }

    public void insert(Phone phone) {
        repository.insert(phone);
    }

    public void update(Phone phone) {
        repository.update(phone);
    }

    public void delete(Phone phone) {
        repository.delete(phone);
    }

    public void deleteAllphones() {
        repository.deleteAllphones();
    }

    public LiveData<List<Phone>> getAllphones() {
        return allphones;
    }
}