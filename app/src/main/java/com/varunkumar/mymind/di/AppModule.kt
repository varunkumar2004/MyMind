package com.varunkumar.mymind.di

import com.varunkumar.mymind.data.BookmarkDao
import com.varunkumar.mymind.data.BookmarkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository {
        return BookmarkRepository(bookmarkDao)
    }
}