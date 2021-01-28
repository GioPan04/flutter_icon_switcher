# **flutter_icon_switcher**

Change your app icon at runtime!

## Known issues
* You can use only one alternative icon. Later on I'll (re)make the process more dynamic. More info [here](https://github.com/GioPan04/flutter_icon_switcher/issues/1)
* The plugin only works on Android, on iOS will work, but i'm working on it.
* On Android if you try to run the app via cli, for example `flutter run` (or from the ide plugin), the app will not launch if the current enabled icon is not the default.

## Installation
1. Add the latest version of the plugin to your `pubpsec.yaml` under dependencies section
2. Run `flutter pub get`
3. Update `android/src/main/AndroidManifest.xml` as follows:
    ```xml
    <application ...>

        <activity
            android:name=".MainActivity"
            android:launchMode="singleTop"
            android:theme="@style/LaunchTheme"
            android:configChanges="orientation|keyboardHidden|keyboard|screenSize|smallestScreenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
            android:hardwareAccelerated="true"
            android:windowSoftInputMode="adjustResize"
            android:enabled="true">

            <meta-data
                android:name="io.flutter.embedding.android.NormalTheme"
                android:resource="@style/NormalTheme"
                />

            <meta-data
                android:name="io.flutter.embedding.android.SplashScreenDrawable"
                android:resource="@drawable/launch_background"
                />

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- The activity-alias are your alternatives icons and name of your app, the default one must be enabled (and the others disabled) and the name must be ".DEFAULT". All the names of your activity-alias' name must begin with a dot. -->

        <!-- FOR NOW USE "ALT" AS ALTERNATIVE ICON NAME, FOLLOW https://github.com/GioPan04/flutter_icon_switcher/issues/1 FOR MORE INFO -->

        <activity-alias
            android:label="Blue"
            android:icon="@mipmap/ic_launcher_3"
            android:name=".ALT"
            android:enabled="false"
            android:targetActivity=".MainActivity">

            <meta-data
                android:name="io.flutter.embedding.android.NormalTheme"
                android:resource="@style/NormalTheme"
                />

            <meta-data
                android:name="io.flutter.embedding.android.SplashScreenDrawable"
                android:resource="@drawable/launch_background"
                />

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

        </activity-alias>
    </application>
    ```
4. In your app you can now use:
    (The name you pass in the method must be in the `AndroidManifest.xml`)
    ```dart
    FlutterIconSwitcher.updateIcon('ALT');
    ```
5. Enjoy!
---
**If you are having issues please fill a issue in the issue tab**