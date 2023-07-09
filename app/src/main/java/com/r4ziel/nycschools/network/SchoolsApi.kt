package com.r4ziel.nycschools.network

import com.r4ziel.nycschools.entitiy.SatScore
import com.r4ziel.nycschools.entitiy.School
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * Created by Jarvis Charles on 7/7/23.
 */

interface SchoolsApi {


    @Headers("ACCEPT: application/json")
    @GET("s3k6-pzi2.json")
   fun fetchSchools(
        @Query(value = "\$\$app_token", encoded = true) appKey: String
   ): Single<List<School>>

   @Headers("ACCEPT: application/json")
   @GET("f9bf-2cp4.json")
   fun fetchSatScores(
       @Query(value = "\$\$app_token", encoded = true) appKey: String
   ):Single<List<SatScore>>
}