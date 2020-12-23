#import "FlutterIconSwitcherPlugin.h"
#if __has_include(<flutter_icon_switcher/flutter_icon_switcher-Swift.h>)
#import <flutter_icon_switcher/flutter_icon_switcher-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_icon_switcher-Swift.h"
#endif

@implementation FlutterIconSwitcherPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterIconSwitcherPlugin registerWithRegistrar:registrar];
}
@end
