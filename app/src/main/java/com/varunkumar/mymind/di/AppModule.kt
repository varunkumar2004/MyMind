package com.varunkumar.mymind.di

import android.content.Context
import androidx.room.Room
import com.varunkumar.mymind.data.BookmarkDao
import com.varunkumar.mymind.data.BookmarkDatabase
import com.varunkumar.mymind.data.BookmarkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BookmarkDatabase {
        return Room
            .databaseBuilder(
                appContext,
                BookmarkDatabase::class.java,
                "bookmark_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBookmarkDao(database: BookmarkDatabase): BookmarkDao {
        return database.bookmarkDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository {
        return BookmarkRepository(bookmarkDao)
    }
}