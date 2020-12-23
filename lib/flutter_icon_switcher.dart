import 'dart:async';
import 'package:flutter/services.dart';

class FlutterIconSwitcher {
  static const MethodChannel _channel = MethodChannel('flutter_icon_switcher');

  /// Sets the icon.
  /// By running this method, the app will close, and only the user can reopen it.
  /// Rember to set the `AndroidManifest.xml` before
  static Future<void> updateIcon(String configurationName) async {
    Map<String,dynamic> data = {
      'name': configurationName,
    };
    await _channel.invokeMethod('updateIcon', data);
  }

  /// Reset the icon to the original state.
  /// Simply this method does the same of [FlutterIconSwitcher.updateIcon] but pass "DEFAULT" as configuration name
  static Future<void> resetIcon() async {
    await _channel.invokeMethod('resetIcon');
  }

}