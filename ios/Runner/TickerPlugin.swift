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
        
        timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(onTimerTick), userInfo: nil, repeats: true)
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
        let timestamp = Int(Date().timeIntervalSince1970 * 1000)
        let message = TickerMessage()
        message.timestamp = NSNumber(value: timestamp)
        sink.onTick(message) {_ in }
    }
}
