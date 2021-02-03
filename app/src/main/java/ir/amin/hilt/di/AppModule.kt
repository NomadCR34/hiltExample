package ir.amin.hilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    @SomeStringFragment
    fun provideSomeString():String{
        return "It's some string!"
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SomeStringFragment