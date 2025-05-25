# Add project specific ProGuard rules here.
# By default, the flags in R8 will keep all classes in the default
# package matching ProGuard Keep rules like "-keep class com.example.** { *; }"
# For more R8 configuration flags, see https://developer.android.com/studio/build/shrink-code

# If you use Kotlin Coroutines, add the following lines:
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.flow.internal.AbstractSharedFlow {
    kotlinx.coroutines.flow.SharedFlowSlot[] susbscribers;
}
-keepclassmembers class kotlinx.coroutines.flow.internal.ChannelFlow {
    kotlinx.coroutines.channels.ReceiveChannel channel;
}
-keepclassmembers class kotlinx.coroutines.flow.internal.FusibleFlow {
    kotlinx.coroutines.channels.ReceiveChannel fusibleRemoteChannel;
}

# If you use Room, add the following lines:
# -keep class androidx.room.** { *; }
# -keep class your.package.name.database.** { *; } # Replace with your database package

# If you use Jetpack Compose, R8 should handle most of it automatically with AGP 7.0+
# However, if you encounter issues, you might need specific rules.
# -keep class androidx.compose.runtime.Composable { *; }
# -keepclassmembers class ** { @androidx.compose.runtime.Composable <methods>; }
# -keepclassmembers class * { @androidx.compose.runtime.Composable <fields>; }

# For Google Drive API / Google Sign-In (often handled by google-services plugin or auto-rules)
# -keep class com.google.api.client.googleapis.extensions.android.gms.auth.** { *; }
# -keep class com.google.api.services.drive.** { *; }

# If using Hilt
# -keepattributes *Annotation*
# -keep class hilt_aggregated_deps.** { *; }
# -keepclassmembers class * { @dagger.hilt.android.internal.lifecycle.HiltViewModelFactory *; }
# -keep class **_HiltModules* { *; }
# -dontwarn dagger.hilt.processor.internal.aggregateddeps.AggregatedDepsProcessor
