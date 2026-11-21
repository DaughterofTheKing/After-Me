
package com.example.afterme.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrustedContactDao {

    @Insert
    void insert(TrustedContact contact);

    @Query("SELECT * FROM trusted_contacts ORDER BY id DESC")
    List<TrustedContact> getAllContacts();
}
