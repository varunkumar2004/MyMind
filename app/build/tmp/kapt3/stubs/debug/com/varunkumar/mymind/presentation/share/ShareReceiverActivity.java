package com.varunkumar.mymind.presentation.share;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\nH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/varunkumar/mymind/presentation/share/ShareReceiverActivity;", "Landroidx/activity/ComponentActivity;", "()V", "bookmarkRepository", "Lcom/varunkumar/mymind/data/BookmarkRepository;", "getBookmarkRepository", "()Lcom/varunkumar/mymind/data/BookmarkRepository;", "setBookmarkRepository", "(Lcom/varunkumar/mymind/data/BookmarkRepository;)V", "handleIncomingIntent", "", "intent", "Landroid/content/Intent;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveText", "text", "app_debug"})
public final class ShareReceiverActivity extends androidx.activity.ComponentActivity {
    @javax.inject.Inject()
    public com.varunkumar.mymind.data.BookmarkRepository bookmarkRepository;
    
    public ShareReceiverActivity() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.varunkumar.mymind.data.BookmarkRepository getBookmarkRepository() {
        return null;
    }
    
    public final void setBookmarkRepository(@org.jetbrains.annotations.NotNull()
    com.varunkumar.mymind.data.BookmarkRepository p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.String handleIncomingIntent(android.content.Intent intent) {
        return null;
    }
    
    private final void saveText(java.lang.String text) {
    }
}