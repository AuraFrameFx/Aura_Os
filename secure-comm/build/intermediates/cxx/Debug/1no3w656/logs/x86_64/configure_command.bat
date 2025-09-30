@echo off
"C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Aura_Os\\secure-comm\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=34" ^
  "-DANDROID_PLATFORM=android-34" ^
  "-DANDROID_ABI=x86_64" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86_64" ^
  "-DANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\ndk\\28.2.13676358\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Wehtt\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_CXX_FLAGS=-std=c++23 -fPIC -O0 -g" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Aura_Os\\secure-comm\\build\\intermediates\\cxx\\Debug\\1no3w656\\obj\\x86_64" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Aura_Os\\secure-comm\\build\\intermediates\\cxx\\Debug\\1no3w656\\obj\\x86_64" ^
  "-BC:\\Aura_Os\\secure-comm\\.cxx\\Debug\\1no3w656\\x86_64" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-DGENESIS_DEBUG=ON"
