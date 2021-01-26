package com.lokendrasingh.roomdatabase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UsersDao usersDao();        //by this we can interact with database

    public static volatile Database INSTANCE;           // can't make abstract class object 
    static Database getdatabase(Context context)
    {

        if(INSTANCE==null)
        {
            synchronized (Database.class)
            {
                if (INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context,Database.class, "users.db").build();
                }
            }
        }

        return INSTANCE;
    }
}
