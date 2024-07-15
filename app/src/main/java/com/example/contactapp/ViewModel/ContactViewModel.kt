package com.example.contactapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.contactapp.data.Database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var database: ContactDatabase) : ViewModel() {
}