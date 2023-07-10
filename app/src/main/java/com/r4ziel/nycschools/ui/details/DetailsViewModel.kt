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

    val satList = MutableLiveData<SatScore>()
    var vmSchoolName: String = ""

    fun updateSchoolName(schoolName: String) {
        vmSchoolName = schoolName
    }

    private fun getMatch(satScores: List<SatScore>) {
        satList.value = satScores.find{satScore -> vmSchoolName.uppercase() == satScore.schoolName }
    }

    /**
     * FetchSatScores: Responsible for fetching data from the SchoolsRepository
     */
    fun fetchSatScores() {
        schoolsRepository.getSatScores()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { satScores ->

                    try {

                        getMatch(satScores)

                    } catch (throwable: Throwable) {

                        satList.value = SatScore("","","","","")
                    }
                },
                {
                    satList.value = SatScore("","","","","")
                }
            )
    }
}