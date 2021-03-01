package com.gilbertthio.restfulreqres.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gilbertthio.restfulreqres.network.Users

class DetailViewModelFactory(private val application: Application, private val user: Users) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return modelClass.getConstructor(Application::class.java, Users::class.java)
                .newInstance(application, user)
        } else throw Exception("Unknown ViewModel")
    }
}