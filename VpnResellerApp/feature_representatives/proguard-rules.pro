# Add project specific ProGuard rules here for this library module.
# Keep ViewModels and Composable functions.
-keep class androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keep public class * implements androidx.compose.runtime.ComposableFunction
-keep public @androidx.compose.runtime.Composable @interface * { *; }
-keepclassmembers public class * {
    @androidx.compose.runtime.Composable *;
}

# If using Hilt for ViewModels
# -keep class * extends androidx.lifecycle.ViewModel
# -keep class dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
# -keep class **_HiltModules* { *; }
