# Add project specific ProGuard rules here for this library module.
# Keep Room entities, DAOs, and TypeConverters
-keep class androidx.room.** { *; }
-keep class com.yourcompany.vpnresellerapp.core_data.database.model.** { *; }
-keep interface com.yourcompany.vpnresellerapp.core_data.database.dao.** { *; }
-keep class com.yourcompany.vpnresellerapp.core_data.database.util.** { *; } # For TypeConverters

# If using SQLCipher
# -keep class net.sqlcipher.** { *; }
# -keep class net.zetetic.database.sqlcipher.** { *; }

# If using Google Drive API client libraries
# -keep class com.google.api.client.** { *; }
# -keep class com.google.api.services.drive.** { *; }
# -dontwarn com.google.api.client.util.GenericData
# -dontwarn com.google.common.base.Preconditions # If using Guava from Drive client

# If using Apache POI
# -keep class org.apache.poi.** { *; }
# -dontwarn org.apache.poi.**
# -dontwarn org.apache.xmlbeans.**
# -dontwarn org.openxmlformats.schemas.**
# -dontwarn org.w3c.dom.**
# -dontwarn javax.xml.**
