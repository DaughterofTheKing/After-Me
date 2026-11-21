package com.example.afterme.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TrustedContact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TrustedContactDao trustedContactDao();
}