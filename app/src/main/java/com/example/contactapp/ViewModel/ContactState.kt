package com.example.contactapp.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contactapp.data.Database.Contact

data class ContactState(
    val contact: List<Contact> = emptyList(),
    val name : MutableState<String> = mutableStateOf(""),
    val number : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val dateOfCreation : MutableState<Long> = mutableStateOf(0L),
)