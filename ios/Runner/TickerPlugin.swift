//
//  TickerPlugin.swift
//  Runner
//
//  Created by Sergei Lavinov on 15.01.2022.
//

import Foundation

class TickerPlugin: NSObject, TickerSender {
    private let sink: TickerReceiver
    private var timer: Timer?
    
    init(binaryMessenger: FlutterBinaryMessenger) {
        sink = TickerReceiver(binaryMessenger: binaryMessenger)
        super.init()
        TickerSenderSetup(binaryMessenger, self)
    }
    
    func start(_ tickerDelay: TickerDelay, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) -> TickerResult? {
        timer?.invalidate()
        
        var interval: Double
        if let delay = tickerDelay.delay {
            // Dart sends delay in milliseconds
            interval = Double(truncating: delay) / 1000
        } else {
            interval = 1
        }
        
        timer = Timer.scheduledTimer(timeInterval: interval, target: self, selector: #selector(onTimerTick), userInfo: nil, repeats: true)
        onTimerTick()
        
        let result = TickerResult()
        result.successful = true
        result.hasError = false
        result.error = nil
        
        return result
    }
    
    func stopWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) -> TickerResult? {
        timer?.invalidate()
        
        let result = TickerResult()
        result.successful = true
        result.hasError = false
        result.error = nil
        
        return result
    }
    
    @objc func onTimerTick() {
        // Dart expects timestamp in milliseconds
        let timestamp = Int(Date().timeIntervalSince1970 * 1000)
        let message = TickerMessage()
        message.timestamp = NSNumber(value: timestamp)
        sink.onTick(message) {_ in }
    }
}
