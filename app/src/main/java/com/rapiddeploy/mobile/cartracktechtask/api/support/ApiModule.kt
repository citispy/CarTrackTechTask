package com.rapiddeploy.mobile.cartracktechtask.api.support

import com.rapiddeploy.mobile.cartracktechtask.ui.search.OmdbResponseCache
import com.rapiddeploy.mobile.cartracktechtask.ui.search.Repository
import com.rapiddeploy.mobile.cartracktechtask.ui.search.TitlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesRepository(webRequestManager: WebRequestManager, omdbResponseCache: OmdbResponseCache) =
        Repository(webRequestManager, omdbResponseCache) as TitlesRepository
}