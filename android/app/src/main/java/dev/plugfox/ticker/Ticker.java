// 
// Generated file. Do not edit manually.
// 
// Copyright (C) 2022 Plague Fox
// Unauthorized copying of this project, via any medium is strictly prohibited
// Proprietary and confidential
// Written by Plague Fox <plugfox@gmail.dev>, 2022
// 
// Flutter calling into Android Steps:
// 1) Add Pigeon as a dev_dependency.
// 2) Make a ".dart" file outside of your "lib" directory for defining the communication interface.
// 3) Run pigeon on your ".dart" file to generate the required Dart and Java code. flutter pub get then flutter pub run pigeon with suitable arguments.
// 4) Add the generated Dart code to ./lib for compilation.
// 5) Add the generated Java code to your ./android/app/src/main/java directory for compilation.
// 6) Implement the generated Java interface for handling the calls on Android, set it up as the handler for the messages.
// 7) Call the generated Dart methods.
// 
// Rules for defining your communication interface:
// 1) The file should contain no method or function definitions, only declarations.
// 2) Datatypes are defined as classes with fields of the supported datatypes (see the supported Datatypes section).
// 3) Api's should be defined as an abstract class with either HostApi() or FlutterApi() as metadata. The former being for procedures that are defined on the host platform and the latter for procedures that are defined in Dart.
// 4) Method declarations on the Api classes should have one argument and a return value whose types are defined in the file or be void.
// 
// Platform channel data types support and codecs:
// + https://flutter.dev/docs/development/platform-integration/platform-channels#codec
// Autogenerated from Pigeon (v1.0.12), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package dev.plugfox.ticker;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Ticker {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class TickerDelay {
    private Long delay;
    public Long getDelay() { return delay; }
    public void setDelay(Long setterArg) { this.delay = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("delay", delay);
      return toMapResult;
    }
    static TickerDelay fromMap(Map<String, Object> map) {
      TickerDelay fromMapResult = new TickerDelay();
      Object delay = map.get("delay");
      fromMapResult.delay = (delay == null) ? null : ((delay instanceof Integer) ? (Integer)delay : (Long)delay);
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class TickerResult {
    private Boolean successful;
    public Boolean getSuccessful() { return successful; }
    public void setSuccessful(Boolean setterArg) { this.successful = setterArg; }

    private Boolean hasError;
    public Boolean getHasError() { return hasError; }
    public void setHasError(Boolean setterArg) { this.hasError = setterArg; }

    private String error;
    public String getError() { return error; }
    public void setError(String setterArg) { this.error = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("successful", successful);
      toMapResult.put("hasError", hasError);
      toMapResult.put("error", error);
      return toMapResult;
    }
    static TickerResult fromMap(Map<String, Object> map) {
      TickerResult fromMapResult = new TickerResult();
      Object successful = map.get("successful");
      fromMapResult.successful = (Boolean)successful;
      Object hasError = map.get("hasError");
      fromMapResult.hasError = (Boolean)hasError;
      Object error = map.get("error");
      fromMapResult.error = (String)error;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class TickerMessage {
    private Long timestamp;
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long setterArg) { this.timestamp = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("timestamp", timestamp);
      return toMapResult;
    }
    static TickerMessage fromMap(Map<String, Object> map) {
      TickerMessage fromMapResult = new TickerMessage();
      Object timestamp = map.get("timestamp");
      fromMapResult.timestamp = (timestamp == null) ? null : ((timestamp instanceof Integer) ? (Integer)timestamp : (Long)timestamp);
      return fromMapResult;
    }
  }
  private static class TickerSenderCodec extends StandardMessageCodec {
    public static final TickerSenderCodec INSTANCE = new TickerSenderCodec();
    private TickerSenderCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return TickerDelay.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)129:         
          return TickerResult.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof TickerDelay) {
        stream.write(128);
        writeValue(stream, ((TickerDelay) value).toMap());
      } else 
      if (value instanceof TickerResult) {
        stream.write(129);
        writeValue(stream, ((TickerResult) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface TickerSender {
    TickerResult start(TickerDelay tickerDelay);
    TickerResult stop();

    /** The codec used by TickerSender. */
    static MessageCodec<Object> getCodec() {
      return TickerSenderCodec.INSTANCE;
    }

    /** Sets up an instance of `TickerSender` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, TickerSender api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TickerSender.start", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              TickerDelay tickerDelayArg = (TickerDelay)args.get(0);
              if (tickerDelayArg == null) {
                throw new NullPointerException("tickerDelayArg unexpectedly null.");
              }
              TickerResult output = api.start(tickerDelayArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TickerSender.stop", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              TickerResult output = api.stop();
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class TickerReceiverCodec extends StandardMessageCodec {
    public static final TickerReceiverCodec INSTANCE = new TickerReceiverCodec();
    private TickerReceiverCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return TickerMessage.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof TickerMessage) {
        stream.write(128);
        writeValue(stream, ((TickerMessage) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java.*/
  public static class TickerReceiver {
    private final BinaryMessenger binaryMessenger;
    public TickerReceiver(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    static MessageCodec<Object> getCodec() {
      return TickerReceiverCodec.INSTANCE;
    }

    public void onTick(TickerMessage tickerMessageArg, Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.TickerReceiver.onTick", getCodec());
      channel.send(new ArrayList<Object>(Arrays.asList(tickerMessageArg)), channelReply -> {
        callback.reply(null);
      });
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
