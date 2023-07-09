package com.r4ziel.nycschools.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r4ziel.nycschools.entitiy.SatScore
import com.r4ziel.nycschools.repository.SchoolsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.stream.Collectors

/**
 * Created by Jarvis Charles on 7/9/23.
 */
class DetailsViewModel(private val schoolsRepository: SchoolsRepository): ViewModel() {

    val satList = MutableLiveData<SatScore>()
    var filteredList = mutableListOf<SatScore>()
    var vmSchoolName: String = ""

    fun updateSchoolName(schoolName: String) {
        vmSchoolName = schoolName
    }

    fun getMatch(satScores: List<SatScore>) {
        satList.value = satScores.find{satScore -> vmSchoolName.uppercase() == satScore.schoolName }
    }

    fun fetchSatScores() {
        schoolsRepository.getSatScores()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { satScores ->

                    try {

                        getMatch(satScores)


                        Log.wtf("Details VM", "School Name is: ${satScores[0].schoolName}" )

                    } catch (throwable: Throwable) {
                        Log.wtf("ViewModel", "Catch Success Error ${throwable.message} ${satScores[0].schoolName}")

                        satList.value = SatScore("","","","","")
                    }

//                    val satScore = satScores
//                        .stream()
//                        .filter { satScore -> schoolName == satScore.schoolName }
//                        .findAny()
//                        .orElse(
//                            SatScore("", "","", "", "", ""))
//                    filteredList = satScores.stream().filter { satScore -> satScore.toString().contains(schoolName) }.collect(Collectors.toList())


//                    satList.value = satScore
                },
                {
                    Log.wtf("Details VM", "School Name is: ${it.message}" )

                }
            )
    }
}