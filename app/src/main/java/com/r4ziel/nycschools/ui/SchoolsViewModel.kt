package com.r4ziel.nycschools.ui

import android.util.Log
import androidx.lifecycle.*
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.repository.SchoolsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsViewModel(private val schoolsRepository: SchoolsRepository, private val savedState: SavedStateHandle): ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _loadingState = MutableStateFlow(SchoolsUIState.Loading(""))
    private val _successState = MutableStateFlow(SchoolsUIState.Success(emptyList()))
    private val _errorState = MutableStateFlow(SchoolsUIState.Error(Throwable()))
    // The UI collects from this StateFlow to get its state updates
    var uiState: StateFlow<SchoolsUIState> = _loadingState

//        savedState.getStateFlow(STATE_FLOW_KEY, _loadingState.value)

    companion object {
        private const val STATE_FLOW_KEY = "STATE_FLOW_KEY"
    }

    init {
        viewModelScope.launch{
            refresh()
        }
    }

    fun refresh(){
        Log.wtf("ViewModel", "Refreshing")


        val currentJob = viewModelScope.launch {

            _loadingState.value = SchoolsUIState.Loading("")
            uiState = _loadingState

            Log.wtf("ViewModel", "State Should Be Loading")

            schoolsRepository.schools
                .catch { exception ->
                    Log.wtf("ViewModel", "Catch Response Error")

                    _errorState.value = SchoolsUIState.Error(exception)
                    uiState = _errorState
                    updateSavedState()
                }
                .collect { response ->

                    Log.wtf("ViewModel", "Collect Response Success")
                    _successState.value = SchoolsUIState.Success(response.blockingFirst())
                    uiState = _successState
                    updateSavedState()
                }
        }
    }

    private fun updateSavedState() {
//         = uiState
    }

    // Represents different states for the Schools screen
    sealed class SchoolsUIState {
        data class Success(val schools: List<School>?): SchoolsUIState()
        data class Error(val exception: Throwable): SchoolsUIState()
        data class Loading(val message:String) : SchoolsUIState()
    }
}