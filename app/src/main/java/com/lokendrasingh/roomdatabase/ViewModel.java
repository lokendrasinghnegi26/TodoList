package com.lokendrasingh.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    UsersRepository usersRepository;
    LiveData<List<Users>> userlist;

    public ViewModel(@NonNull Application application) {
        super(application);
        usersRepository= new UsersRepository(application);
        userlist= usersRepository.getAllUsers();        // from UserRepository.java there is a function getAllUsers() called for userlist.
    }

    public LiveData<List<Users>> getAllUsers()
    {
        return usersRepository.getAllUsers();
    }
    public void insertuser(Users users)
    {
        usersRepository.insertuser(users);
    }
    public void updateuser(Users users)
    {
        usersRepository.updateusers(users);
    }

    public void deleteuser(Users users)
    {
        usersRepository.Deleteuser(users);
    }
}
