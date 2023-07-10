package com.r4ziel.nycschools.repository


import com.r4ziel.a20230707_jarvischarles_nycschools.BuildConfig
import com.r4ziel.nycschools.entitiy.SatScore
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.network.SchoolsApi
import io.reactivex.rxjava3.core.Single

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRepository(private val api: SchoolsApi) {

    fun getSchools(): Single<List<School>> = api.fetchSchools(BuildConfig.APPTOKEN)

    fun getSatScores(): Single<List<SatScore>> = api.fetchSatScores(BuildConfig.APPTOKEN)
}