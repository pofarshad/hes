# Add any ProGuard rules here that should be automatically applied to
# consumers of this library. Keep -keep class rules that are necessary
# for consumers of your library here.
#
# Examples:
#
# -keep class com.example.MyClass
# -keep class com.example.MyClass {
#   public <fields>;
#   public <methods>;
# }
# -keep interface com.example.MyInterface
# -keep interface com.example.MyInterface {
#   <methods>;
# }

# Keep all Composables if they are public
-keep public class * implements androidx.compose.runtime.ComposableFunction
-keep public @androidx.compose.runtime.Composable @interface * { *; }
-keepclassmembers public class * {
    @androidx.compose.runtime.Composable *;
}
