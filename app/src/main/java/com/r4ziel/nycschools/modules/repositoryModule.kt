package com.r4ziel.nycschools.modules

import com.r4ziel.nycschools.repository.SchoolsRepository
import com.r4ziel.nycschools.network.SchoolsRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Jarvis Charles on 7/7/23.
 */
val repositoryModule = module {
    singleOf(::SchoolsRepository)
    singleOf(::SchoolsRemoteDataSource)
}