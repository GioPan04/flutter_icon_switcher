/*
Copyright 2020 Gioele Pannetto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package it.gioelepannetto.flutter_icon_switcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterIconSwitcherPlugin */
public class FlutterIconSwitcherPlugin implements FlutterPlugin, MethodCallHandler {

  static private String TAG = "FlutterIconSwitcherPlugin";
  Context context;

  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_icon_switcher");
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "updateIcon":
        try {
          String data = call.argument("name");
          updateIcon(data);
          result.success(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case "resetIcon":
        try {
          resetIcon();
          result.success(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      default:
        result.notImplemented();
    }
  }


  public void updateIcon(@NonNull String name) {

    // Get the packageName of the app
    String packageName = context.getPackageName();

    // Get the class name of the activity-alias
    String className = String.format("%s.%s", packageName, name);

    ActivityInfo[] oldName = getActivities();

    PackageManager pm = context.getPackageManager();

    pm.setComponentEnabledSetting(
            new ComponentName(packageName, className),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
    );

    for(ActivityInfo activity: oldName) {
      if(!activity.name.equals(className)) {
        pm.setComponentEnabledSetting(
                new ComponentName(packageName, activity.name),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
      }
    }
  }

  public void resetIcon() {
    // Get the packageName of the app
    String packageName = context.getPackageName();

    // Get the default class name of the activity-alias
    String defaultClassName = String.format("%s.%s", packageName, "MainActivity");

    // Update the icon
    PackageManager pm = context.getPackageManager();

    // ActivityInfo oldActivity = getEnabledComponent();
    String oldClassName = String.format("%s.%s", packageName, "ALT");

    /*if (oldActivity.name.contains("DEFAULT")) {
      return;
    }*/

    pm.setComponentEnabledSetting(
            new ComponentName(packageName, defaultClassName),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
    );

    pm.setComponentEnabledSetting(
            new ComponentName(packageName, oldClassName),
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
    );


  }


  public ActivityInfo getEnabledComponent() {
    PackageManager pm = context.getPackageManager();
    String packageName = context.getPackageName();
    ActivityInfo[] activityInfos = getActivities();

    ActivityInfo enabledComponent = null;
    int i = 0;

    for(ActivityInfo currentComponent: activityInfos) {
      if (currentComponent.enabled) {
        enabledComponent = currentComponent;
      }

      Log.d(TAG, Integer.toString(i) + " " + currentComponent + ": " + Boolean.toString(currentComponent.enabled));
      i++;
    }

    return enabledComponent;
  }

  public ActivityInfo[] getActivities() {
    ActivityInfo[] activityInfos;

    PackageManager pm = context.getPackageManager();
    String packageName = context.getPackageName();

    try {
      PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES | PackageManager.GET_DISABLED_COMPONENTS);
      activityInfos = info.activities;

      Log.d(TAG, "Found this configured activities:");
      for(ActivityInfo activityInfo : activityInfos) {
        Log.d(TAG, activityInfo.name);
      }

      return activityInfos;

    } catch (PackageManager.NameNotFoundException e) {
      Log.e(TAG, e.toString());
    }
    return null;
  }


}
