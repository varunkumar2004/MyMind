package com.varunkumar.mymind.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/varunkumar/mymind/data/BookmarkDao;", "", "deleteBookmark", "", "bookmark", "Lcom/varunkumar/mymind/data/models/Bookmark;", "(Lcom/varunkumar/mymind/data/models/Bookmark;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBookmarks", "Lkotlinx/coroutines/flow/Flow;", "", "getBookmarkById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBookmark", "app_debug"})
@androidx.room.Dao()
public abstract interface BookmarkDao {
    
    @androidx.room.Query(value = "SELECT * FROM bookmarks")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.varunkumar.mymind.data.models.Bookmark>> getAllBookmarks();
    
    @androidx.room.Query(value = "SELECT * FROM bookmarks WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBookmarkById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.varunkumar.mymind.data.models.Bookmark> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBookmark(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.models.Bookmark bookmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBookmark(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.models.Bookmark bookmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}