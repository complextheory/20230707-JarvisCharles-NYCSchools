package com.r4ziel.nycschools.network

import com.r4ziel.nycschools.entitiy.School
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Jarvis Charles on 7/7/23.
 */

interface SchoolsApi {

    @GET("s3k6-psi2.json")
   suspend fun fetchSchools(): List<School>

//    @GET("f9bf-2cp4.json")
//   suspend fun fetchSatResults(): List<Sat>
}