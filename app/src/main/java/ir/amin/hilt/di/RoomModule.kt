package ir.amin.hilt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.amin.hilt.room.BlogDao
import ir.amin.hilt.room.BlogDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogData(@ApplicationContext context: Context):BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.blogDao()
    }

}