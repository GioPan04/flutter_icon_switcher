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
    await FlutterIconSwitcher.updateIcon('BLUE');
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
