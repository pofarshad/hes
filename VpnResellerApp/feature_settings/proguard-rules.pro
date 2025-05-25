# Add project specific ProGuard rules here for this library module.
-keep class androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keep public class * implements androidx.compose.runtime.ComposableFunction
-keep public @androidx.compose.runtime.Composable @interface * { *; }
-keepclassmembers public class * {
    @androidx.compose.runtime.Composable *;
}

# For WorkManager if used directly or its workers are defined here
# -keepclassmembers class * extends androidx.work.Worker {
#    public <init>(android.content.Context,androidx.work.WorkerParameters);
# }
