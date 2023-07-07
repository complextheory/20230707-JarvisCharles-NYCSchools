package com.r4ziel.nycschools.repository

import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.network.SchoolsRemoteDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRepository(private val schoolsRemoteDataSource: SchoolsRemoteDataSource) {


    val schools: Flow<List<School>> = schoolsRemoteDataSource.schools

}