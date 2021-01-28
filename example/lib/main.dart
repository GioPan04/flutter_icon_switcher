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

import 'package:flutter/material.dart';
import 'package:flutter_icon_switcher/flutter_icon_switcher.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {


  void _updateIcon() async {
    await FlutterIconSwitcher.updateIcon('ALT');
  }

  void _resetIcon() async { 
    await FlutterIconSwitcher.resetIcon();
  }


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter Icon Switcher'),
        ),
        body: Center(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              RaisedButton(onPressed: _updateIcon, child: Text("CHANGE ICON"),),
              RaisedButton(onPressed: _resetIcon, child: Text("RESET ICON"),),
            ]
          ),
        ),
      ),
    );
  }
}
