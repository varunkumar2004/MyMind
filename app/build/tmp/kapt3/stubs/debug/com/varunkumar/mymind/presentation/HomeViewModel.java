package com.varunkumar.mymind.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR*\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/varunkumar/mymind/presentation/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "bookmarkRepository", "Lcom/varunkumar/mymind/data/BookmarkRepository;", "(Lcom/varunkumar/mymind/data/BookmarkRepository;)V", "_bookmarks", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/varunkumar/mymind/data/models/Bookmark;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/varunkumar/mymind/presentation/HomeState;", "filterBookmarks", "Lkotlinx/coroutines/flow/StateFlow;", "getFilterBookmarks", "()Lkotlinx/coroutines/flow/StateFlow;", "<set-?>", "", "searchQuery", "getSearchQuery", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "state", "getState", "delete", "", "bookmark", "getBookmarkById", "id", "", "insert", "onSearchQueryChange", "query", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.varunkumar.mymind.data.BookmarkRepository bookmarkRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.varunkumar.mymind.presentation.HomeState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.varunkumar.mymind.data.models.Bookmark>> _bookmarks = null;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> searchQuery;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.varunkumar.mymind.data.models.Bookmark>> filterBookmarks = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.varunkumar.mymind.presentation.HomeState> state = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.BookmarkRepository bookmarkRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.varunkumar.mymind.data.models.Bookmark>> getFilterBookmarks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.varunkumar.mymind.presentation.HomeState> getState() {
        return null;
    }
    
    public final void onSearchQueryChange(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void getBookmarkById(int id) {
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.models.Bookmark bookmark) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.models.Bookmark bookmark) {
    }
}