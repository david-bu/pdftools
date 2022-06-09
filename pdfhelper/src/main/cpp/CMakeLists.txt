# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)
#cmake_policy(SET CMP0077)

# Declares and names the project.

project("pdfhelper")

set(lib_src_DIR ${CMAKE_CURRENT_SOURCE_DIR})

set(lib_build_DIR ${CMAKE_CURRENT_BINARY_DIR})
file(MAKE_DIRECTORY ${lib_build_DIR})

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             pdfhelper

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             pdfhelper.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

set(FREETYPE_LIBRARY "${lib_src_DIR}/freetype")
set(FREETYPE_INCLUDE_DIRS "${lib_src_DIR}/freetype/include")

set(ENABLE_UNSTABLE_API_ABI_HEADERS OFF)
set(BUILD_GTK_TESTS OFF)
set(BUILD_QT5_TESTS OFF)
set(BUILD_QT6_TESTS OFF)
set(BUILD_CPP_TESTS OFF)
set(BUILD_MANUAL_TESTS OFF)
set(ENABLE_BOOST ON)
set(ENABLE_UTILS OFF)
set(ENABLE_CPP ON)
set(ENABLE_GLIB ON)
set(ENABLE_GOBJECT_INTROSPECTION OFF)
set(ENABLE_GTK_DOC OFF)
set(ENABLE_QT5 OFF)
set(ENABLE_QT6 OFF)
set(ENABLE_LIBCURL ON)
set(ENABLE_ZLIB ON)
set(ENABLE_ZLIB_UNCOMPRESS OFF)
set(USE_FLOAT OFF)
set(BUILD_SHARED_LIBS OFF)
set(RUN_GPERF_IF_PRESENT OFF)

set(tmp ${CMAKE_MODULE_PATH}) # save module path; poppler will override this
add_subdirectory(
        ${lib_src_DIR}/poppler ${lib_build_DIR}/poppler
)
set(CMAKE_MODULE_PATH "${tmp};${CMAKE_MODULE_PATH}") # restore and append poppler module path

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       pdfhelper

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )