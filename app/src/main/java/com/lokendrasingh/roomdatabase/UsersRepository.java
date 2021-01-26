package com.lokendrasingh.roomdatabase;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

// this repository links our data from Dao to Dabatabase
public class UsersRepository {
    private Database database;
    private UsersDao usersDao;
    private LiveData<List<Users>> userlist;

    public UsersRepository(Application application) {
        database= Database.getdatabase(application);
        usersDao= database.usersDao();
        userlist= usersDao.getAllUsers();


    }
    public LiveData<List<Users>>getAllUsers()
    {
        return database.usersDao().getAllUsers();
    }
    public void insertuser(Users users)
    {
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().insertUser(users);
                return null;
            }
        }.execute();
    }
    public void  updateusers(final Users users)
    {
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().Updateusers(users);
                return null;
            }
        }.execute();
    }
    public  void Deleteuser(final Users users)
    {
        new AsyncTask<Void, Void, Void>(

        ) {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().Deleteusers(users);
                return null;
            }
        }.execute();
    }
}
