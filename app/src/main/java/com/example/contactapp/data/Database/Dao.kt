package com.example.contactapp.data.Database

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface Dao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts_table ORDER BY user_name ASC")
    fun getContactsSortName(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY dateOfCreation DESC")
    fun getContactsSortDate(): Flow<List<Contact>>

}