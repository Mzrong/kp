package com.zanqianba.kp.kp

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Build;
import android.os.Environment
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import androidx.core.content.FileProvider
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File

/** KpPlugin */
class KpPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  private lateinit var context : Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "kp")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if (call.method == "getPhotoPath") {
      getPhotoPath(call, result);
    } else if (call.method == "getPhotoAlbumPath") {
      getPhotoAlbumPath(call, result);
    } else if (call.method == "getAndroidVersion") {
      getAndroidVersion(call, result);
    } else if (call.method == "getAbsolutePath") {
      getAbsolutePath(call, result);
    } else if (call.method == "refreshMedia") {
      refreshMedia(call, result);
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun getPhotoPath(@NonNull call: MethodCall, @NonNull result: Result) {
    val storePath =  Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_PICTURES
    result.success(storePath)
  }

  private fun getPhotoAlbumPath(@NonNull call: MethodCall, @NonNull result: Result) {
    val storePath =  Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera"
    result.success(storePath)
  }

  private fun getAndroidVersion(@NonNull call: MethodCall, @NonNull result: Result) {
    result.success(Build.VERSION.RELEASE)
  }

  private fun getAbsolutePath(@NonNull call: MethodCall, @NonNull result: Result) {
    val storePath =  Environment.getExternalStorageDirectory().absolutePath
    result.success(storePath)
  }

  private fun refreshMedia(@NonNull call: MethodCall, @NonNull result: Result) {
    val path: String? = call.argument("path")
    if (path != null) {
      val file = File(path)
//      val fileUri: Uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
      MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), null) {
        p, u ->
        if (u != null) {

        }
      }
    }
  }
}
