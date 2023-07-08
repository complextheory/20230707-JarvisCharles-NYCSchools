package com.r4ziel.nycschools.repository


import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.network.SchoolsApi
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRepository(private val api: SchoolsApi) {

    fun getSchools(): Observable<Response<List<School>>> = api.fetchSchools("rbccFODNzIdbx1bpvXh1LtbMh")


//    val schools: Flow<Response<List<School>>> = schoolsRemoteDataSource.schools

}