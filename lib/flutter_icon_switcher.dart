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