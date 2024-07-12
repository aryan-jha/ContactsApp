package com.example.contactapp.data

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
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

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class ContactDatabase : RoomDatabase(){
    abstract val dao : Dao
}

@Entity(tableName = "contacts_table")
data class Contact(

    @PrimaryKey(autoGenerate = true)var id: Int,
    @ColumnInfo(name = "user_name")var name: String,
    var number: String,
    var email: String,
    var dateOfCreation : Long,
    var isActive: Boolean

)