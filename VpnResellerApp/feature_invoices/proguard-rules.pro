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
