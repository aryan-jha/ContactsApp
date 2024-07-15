package com.example.contactapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.Database.Contact
import com.example.contactapp.data.Database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
//import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var database: ContactDatabase) : ViewModel() {

    private val isSortedByName = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var contact = isSortedByName.flatMapLatest {
        if (it) {
            database.dao.getContactsSortName()
        } else {
            database.dao.getContactsSortDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())

    val state = combine(_state, contact, isSortedByName) { _state, contacts, isSortedByName ->

        _state.copy(contact = contacts)

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),ContactState())

    fun saveContact(){
        val contactSave = Contact(
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            dateOfCreation = System.currentTimeMillis().toLong(),
            isActive = true
        )
        viewModelScope.launch {
            database.dao.upsertContact(contactSave)
        }
    }

}