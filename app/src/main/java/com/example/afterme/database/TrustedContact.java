
package com.example.afterme.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trusted_contacts")
public class TrustedContact {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String relationship;
    public String phoneNumber;
}
