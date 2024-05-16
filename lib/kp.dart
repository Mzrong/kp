
import 'dart:async';

import 'package:flutter/services.dart';

class Kp {
  static const MethodChannel _channel =
      const MethodChannel('kp');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }


  static Future<String?> get photoPath async {
    final String? photoPath = await _channel.invokeMethod('getPhotoPath');
    return photoPath;
  }

  static Future<String?> get getPhotoAlbumPath async {
    final String? photoPath = await _channel.invokeMethod('getPhotoAlbumPath');
    return photoPath;
  }

  static Future<String?> get getAndroidVersion async {
    final String? version = await _channel.invokeMethod('getAndroidVersion');
    return version;
  }

  static Future<String?> get getAbsolutePath async {
    final String? path = await _channel.invokeMethod('getAbsolutePath');
    return path;
  }

  static refreshMedia(String path) async {
    await _channel.invokeMethod('refreshMedia', {"path": path});
  }
}
