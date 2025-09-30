@echo off
"C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.31.6\\bin\\cmake.exe" ^
  "-HC:\\Aura_Os\\datavein-oracle-native\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=34" ^
  "-DANDROID_PLATFORM=android-34" ^
  "-DANDROID_ABI=armeabi-v7a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=armeabi-v7a" ^
  "-DANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.31.6\\bin\\ninja.exe" ^
  "-DCMAKE_CXX_FLAGS=-std=c++23 -fPIC -O0 -g" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Aura_Os\\datavein-oracle-native\\build\\intermediates\\cxx\\Debug\\4u4i6p72\\obj\\armeabi-v7a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Aura_Os\\datavein-oracle-native\\build\\intermediates\\cxx\\Debug\\4u4i6p72\\obj\\armeabi-v7a" ^
  "-BC:\\Aura_Os\\datavein-oracle-native\\.cxx\\Debug\\4u4i6p72\\armeabi-v7a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-DGENESIS_DEBUG=ON"
