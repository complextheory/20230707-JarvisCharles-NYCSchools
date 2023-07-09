package com.r4ziel.nycschools.repository


import com.r4ziel.nycschools.entitiy.SatScore
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.network.SchoolsApi
import com.r4ziel.nycschools.network.SchoolsRemoteDataSource
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRepository(private val schoolsRemoteDataSource: SchoolsRemoteDataSource, private val api: SchoolsApi) {

    fun getSchools(): Observable<List<School>> = api.fetchSchools("rbccFODNzIdbx1bpvXh1LtbMh")

    fun getSatScores(): Observable<List<SatScore>> = api.fetchSatScores("rbccFODNzIdbx1bpvXh1LtbMh")

//    val schools: Flow<Observable<List<School>>> = schoolsRemoteDataSource.schools

}