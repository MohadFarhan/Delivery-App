# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\10_Projects\1_IDEs_and_SDKs\Android_SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile


# Save the obfuscation mapping to a file, so you can de-obfuscate any stack
# traces later on.
-printmapping bin/classes-processed.map

# You can print out the seeds that are matching the keep options below.
#-printseeds bin/classes-processed.seeds


# Preverification is irrelevant for the dex compiler and the Dalvik VM.
-dontpreverify


# Reduce the size of the output some more.
-repackageclasses ''
-allowaccessmodification

# Switch off some optimizations that trip older versions of the Dalvik VM.
-optimizations !code/simplification/arithmetic

-optimizationpasses 3
#-overloadaggressively


# Preserve all fundamental application classes.
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Preserve all View implementations, their special context constructors, and
# their setters.
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# Preserve all classes that have context constructors, and the
# constructors themselves.
-keepclassmembers class * {
  public <init>(android.content.Context);
}

# Preserve all classes that have context constructors and a variable set
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Preserve all classes that have context constructors, and the
# constructors themselves.

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Preserve the special fields of all Parcelable implementations.
-keepclassmembers class * implements android.OS.Parcelable {
    static android.OS.Parcelable$Creator CREATOR;
}

# Preserve static fields of inner classes of R classes that might be accessed
# through introspection.
-keepclassmembers class **.R$* {
  public static <fields>;
}

# The Android Compatibility library references some classes that may not be
# present in all versions of the API, but we know that's ok.
-dontwarn android.support.**

# Preserve the special static methods that are required in all enumeration
# classes.
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your application doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Your application may contain more items that need to be preserved;
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface


-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-dontwarn com.squareup.picasso.*
-dontwarn rx.internal.**
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn com.googlecode.mp4parser.**
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource



-keep class javax.annotation.concurrent.**
-dontwarn javax.annotation.concurrent.**
-keep class android.arch.persistence.room.paging.LimitOffsetDataSource
-keep interface android.arch.persistence.room.paging.LimitOffsetDataSource
-keep class android.arch.util.paging.CountedDataSource
-keep interface android.arch.util.paging.CountedDataSource
-keep class com.lala.move.farhan.challenge.domains.**
-keepclassmembers class com.lala.move.farhan.challenge.domain.** { *; }
-keep interface com.lala.move.farhan.challenge.**
-keepclassmembers interface com.lala.move.farhan.challenge.** { *; }
-keep enum com.lala.move.farhan.challenge.**
-keepclassmembers enum com.lala.move.farhan.challenge.** { *; }

