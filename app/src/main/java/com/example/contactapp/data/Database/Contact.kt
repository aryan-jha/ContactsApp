package com.example.contactapp.data.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(

    @PrimaryKey(autoGenerate = true)var id: Int,
    @ColumnInfo(name = "user_name")var name: String,
    var number: String,
    var email: String,
    var dateOfCreation : Long,
    var isActive: Boolean

)