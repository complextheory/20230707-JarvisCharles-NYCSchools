package com.r4ziel.nycschools.ui.schools

import android.util.Log
import androidx.lifecycle.*
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.repository.SchoolsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsViewModel(
    private val schoolsRepository: SchoolsRepository,
): ViewModel() {

    var schoolsLiveData = MutableLiveData<List<School>>()

    var errorHandlerLiveData = MutableLiveData<Throwable>()

    /**
     * Refresh: Responsible for refresh of schools data from the repository as well as,
     * initial fetch from onResume
     */
    fun refreshData(){
        Log.wtf("ViewModel", "Refreshing")

        schoolsRepository.getSchools()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

            {
                Log.wtf("ViewModel", "Collect Response Success")

                try {
                    schoolsLiveData.value = it
                }catch (throwable: Throwable) {
                    errorHandlerLiveData.value = throwable
                    Log.wtf("ViewModel", "Catch Success Error ${throwable.message}")

                }

            },
            {

                Log.wtf("ViewModel", "Catch Response Error")
                errorHandlerLiveData.value = it
                schoolsLiveData.value = emptyList()
            }
        )
    }
}