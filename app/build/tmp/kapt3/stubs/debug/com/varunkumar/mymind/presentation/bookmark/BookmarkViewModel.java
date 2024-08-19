package com.varunkumar.mymind.presentation.bookmark;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00182\u0006\u0010\u0006\u001a\u00020\u0007J$\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00182\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u000e\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0011J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0011J\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u0011J\u0006\u0010$\u001a\u00020\u001bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/varunkumar/mymind/presentation/bookmark/BookmarkViewModel;", "Landroidx/lifecycle/ViewModel;", "bookmarkRepository", "Lcom/varunkumar/mymind/data/BookmarkRepository;", "textRecognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "context", "Landroid/content/Context;", "(Lcom/varunkumar/mymind/data/BookmarkRepository;Lcom/google/mlkit/vision/text/TextRecognizer;Landroid/content/Context;)V", "_bookmark", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/varunkumar/mymind/data/models/Bookmark;", "bookmark", "Lkotlinx/coroutines/flow/StateFlow;", "getBookmark", "()Lkotlinx/coroutines/flow/StateFlow;", "imageToText", "", "getImageToText$annotations", "()V", "getImageToText", "images", "", "analyseImage", "Lkotlinx/coroutines/flow/Flow;", "analyseImages", "deleteBookmark", "", "id", "", "onBookmarkImageChange", "image", "onBookmarkTextChange", "text", "onBookmarkTitleChange", "title", "upsertBookmark", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BookmarkViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.varunkumar.mymind.data.BookmarkRepository bookmarkRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.text.TextRecognizer textRecognizer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.varunkumar.mymind.data.models.Bookmark> _bookmark = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.varunkumar.mymind.data.models.Bookmark> bookmark = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> images = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> imageToText = null;
    
    @javax.inject.Inject()
    public BookmarkViewModel(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.BookmarkRepository bookmarkRepository, @org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.text.TextRecognizer textRecognizer, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.varunkumar.mymind.data.models.Bookmark> getBookmark() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getImageToText() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    public static void getImageToText$annotations() {
    }
    
    public final void getBookmark(int id) {
    }
    
    public final void onBookmarkTitleChange(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    public final void onBookmarkTextChange(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void onBookmarkImageChange(@org.jetbrains.annotations.Nullable()
    java.lang.String image) {
    }
    
    public final void deleteBookmark() {
    }
    
    public final void upsertBookmark() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> analyseImage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> analyseImages(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> images) {
        return null;
    }
}