package com.gilbertthio.restfulreqres.ui.main

import androidx.lifecycle.*
import com.gilbertthio.restfulreqres.network.ReqresApi.reqresApiServices
import com.gilbertthio.restfulreqres.network.Users
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /**
     * A set of live data and its backing data for the list of [Users]
     * received from the server using [reqresApiServices]
     */
    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>>
        get() = _users

    /**
     * A set of live data and its backing data to keep track of the
     * page the user is in
     */
    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int> get() = _page
    val pageString = Transformations.map(_page) { it?.toString() ?: "1" }

    /**
     * A set of live data and its backing data to keep track of the
     * maximum number of pages in the [reqresApiServices] server
     */
    private val _maxPages = MutableLiveData<Int>()
    val maxPage: LiveData<Int> get() = _maxPages
    val maxPagesString = Transformations.map((_maxPages)) { it?.toString() ?: "1" }

    /**
     * A set of live data and its backing data for the fragment to
     * monitor and toast if its not null
     */
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    /**
     * A set of live data and its backing data to keep track of the
     * item selected in the recycler view
     */
    private val _selectedItem = MutableLiveData<Users?>()
    val selectedItem: LiveData<Users?>
        get() = _selectedItem

    init {
        _selectedItem.value = null
        _message.value = "Attempting to fetch data.."
        _page.value = 1
        _maxPages.value = 1
        retrieveDataFromPage()
    }

    /**
     * Retrieve the data from the next page of the users list
     */
    fun nextPage() {
        _page.value?.let { currentPage ->
            if (currentPage < _maxPages.value ?: 1) {
                _page.value = currentPage + 1
                retrieveDataFromPage()
            }
        }
    }

    /**
     * Retrieve the data from the previous page of the users list
     */
    fun previousPage() {
        _page.value?.let { currentPage ->
            if (currentPage > 1) {
                _page.value = currentPage - 1
                retrieveDataFromPage()
            }
        }
    }

    /**
     * Handles item click for the recycler view
     */
    fun onItemClicked(user: Users) {
        _selectedItem.value = user
    }

    /**
     * Resets the value of [_selectedItem] to prevent a CTD caused by an
     * infinite loop
     */
    fun onDoneNavigating() {
        _selectedItem.value = null
    }

    /**
     * Retrieve data from the [reqresApiServices] and display it to the user
     */
    private fun retrieveDataFromPage() {
        viewModelScope.launch {
            try {
                // Fetches data and store it into the view model
                val response = reqresApiServices.getUsers(_page.value ?: 1)
                _maxPages.value = response.pages
                _users.value = response.data
            } catch (exception: Exception) {
                _message.value = exception.message
            }
        }
    }
}