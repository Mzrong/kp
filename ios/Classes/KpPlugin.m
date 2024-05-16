#import "KpPlugin.h"
#if __has_include(<kp/kp-Swift.h>)
#import <kp/kp-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "kp-Swift.h"
#endif

@implementation KpPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftKpPlugin registerWithRegistrar:registrar];
}
@end
