package com.r4ziel.nycschools.ui

import androidx.lifecycle.*
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.repository.SchoolsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsViewModel(private val schoolsRepository: SchoolsRepository, private val savedState: SavedStateHandle): ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _loadingState = MutableStateFlow(SchoolsUIState.Loading("")).asStateFlow()
    private val _successState = MutableStateFlow(SchoolsUIState.Success(emptyList()))
    private val _errorState = MutableStateFlow(SchoolsUIState.Error(Throwable()))
    // The UI collects from this StateFlow to get its state updates
    var uiState: StateFlow<SchoolsUIState> = savedState.getStateFlow(STATE_FLOW_KEY, _loadingState.value)

    companion object {
        private const val STATE_FLOW_KEY = "STATE_FLOW_KEY"
    }

    init {
        refresh()
    }

    fun refresh(){

        viewModelScope.launch {

            uiState = _loadingState

            schoolsRepository.getSchools().observeOn(Schedulers.newThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    _successState.value = SchoolsUIState.Success(it.body())
                    uiState = _successState
                    updateSavedState()
                },
                {
                    _errorState.value = SchoolsUIState.Error(it)
                    uiState = _errorState
                    updateSavedState()
                }
            )

//            schoolsRepository.schools
//                .catch { exception ->
//                    _errorState.value = SchoolsUIState.Error(exception)
//                    uiState = _errorState
//                    updateSavedState()
//                }
//                .collect { response ->
//                    if (response.isSuccessful) {
//                    _successState.value = SchoolsUIState.Success(response.body())
//                    uiState = _successState
//                    updateSavedState() }
//            }
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