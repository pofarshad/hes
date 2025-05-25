# Keep Room entities and DAOs for consumers
-keep class com.vpnreseller.core_data.local.entity.** { *; }
-keep class com.vpnreseller.core_data.local.dao.** { *; }

# Keep Hilt generated classes for consumers
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
