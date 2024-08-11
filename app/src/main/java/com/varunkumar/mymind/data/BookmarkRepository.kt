package com.varunkumar.mymind.data

import com.varunkumar.mymind.data.models.Bookmark

class BookmarkRepository(
    private val bookmarkDao: BookmarkDao
) {
    val allBookmarks = bookmarkDao.getAllBookmarks()

    suspend fun insert(bookmark: Bookmark) {
        bookmarkDao.insertBookmark(bookmark)
    }

    suspend fun getBookmarkById(id: Int): Bookmark {
        return bookmarkDao.getBookmarkById(id)
    }

    suspend fun delete(bookmark: Bookmark) {
        bookmarkDao.deleteBookmark(bookmark)
    }
}
