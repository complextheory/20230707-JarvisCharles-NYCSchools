package com.r4ziel.nycschools.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.repository.SchoolsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsViewModel(private val schoolsRepository: SchoolsRepository): ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _loadingState = MutableStateFlow(SchoolsUIState.Loading(""))
    private val _successState = MutableStateFlow(SchoolsUIState.Success(emptyList()))
    private val _errorState = MutableStateFlow(SchoolsUIState.Error(Throwable()))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<SchoolsUIState> = _loadingState

    init {
        viewModelScope.launch {

            _loadingState.value = SchoolsUIState.Loading("")

            schoolsRepository.schools
                .catch { exception -> _errorState.value = SchoolsUIState.Error(exception) }
                .collect{ schools -> _successState.value = SchoolsUIState.Success(schools) }
        }
    }




    // Represents different states for the Schools screen
    sealed class SchoolsUIState {
        data class Success(val schools: List<School>): SchoolsUIState()
        data class Error(val exception: Throwable): SchoolsUIState()
        data class Loading(val message:String) : SchoolsUIState()
    }
}