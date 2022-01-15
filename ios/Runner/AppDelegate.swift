import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  private var tickerPlugin: TickerPlugin?
    
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
      
    if let controller = window?.rootViewController as? FlutterViewController {
      tickerPlugin = TickerPlugin(binaryMessenger: controller.binaryMessenger)
    }
      
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
