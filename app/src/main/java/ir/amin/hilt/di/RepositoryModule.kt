package ir.amin.hilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.amin.hilt.repository.MainRepository
import ir.amin.hilt.retrofit.BlogRetrofit
import ir.amin.hilt.retrofit.NetworkMapper
import ir.amin.hilt.room.BlogDao
import ir.amin.hilt.room.CacheMapper
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ):MainRepository{
        return MainRepository(
            blogDao,
            retrofit,
            cacheMapper,
            networkMapper
        )
    }


}