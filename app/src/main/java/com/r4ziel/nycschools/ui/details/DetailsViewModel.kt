package com.r4ziel.nycschools.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4ziel.nycschools.entitiy.SatScore
import com.r4ziel.nycschools.repository.SchoolsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Jarvis Charles on 7/9/23.
 */
class DetailsViewModel(private val schoolsRepository: SchoolsRepository): ViewModel() {

    val satList = MutableLiveData<List<SatScore>>()
    var schoolName = ""


    fun fetchSatScores() {
        schoolsRepository.getSatScores()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { satScores ->
                    satList.value = satScores.filter { it.schoolName == schoolName }
                },
                {
                    satList.value = emptyList()
                }
            )
    }
}