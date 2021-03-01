package com.gilbertthio.restfulreqres.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.gilbertthio.restfulreqres.R
import com.gilbertthio.restfulreqres.network.Users

class DetailViewModel(application: Application, user: Users) : AndroidViewModel(application) {

    /**
     * A set of live data and its backing data for the
     * user selected in MainFragment
     */
    private val _selectedUser = MutableLiveData<Users>()
    val selectedUser: LiveData<Users> get() = _selectedUser

    /**
     * Display the full name of the user
     */
    val fullName = Transformations.map(_selectedUser) { data ->
        data.let {
            application.getString(R.string.full_name, data.firstName, data.lastName)
        }
    }

    init {
        _selectedUser.value = user
    }
}