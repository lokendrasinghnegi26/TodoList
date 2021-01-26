  package com.lokendrasingh.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {
    @Insert
    void insertUser(Users users);

    @Update
    void Updateusers(Users users);

    @Delete
    void Deleteusers(Users users);

    @Query("SELECT * from USERS")
    LiveData<List<Users>> getAllUsers();        // activity can observe there will be changes in database( update , delete and insertion any time in database)
}
