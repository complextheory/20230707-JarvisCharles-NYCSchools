package com.r4ziel.nycschools.modules

import com.r4ziel.nycschools.network.SchoolsRemoteDataSource

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Jarvis Charles on 7/7/23.
 */
val dataModule = module {
    singleOf(::SchoolsRemoteDataSource)
}