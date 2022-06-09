package de.david.busse.pdfhelper

class NativeLib {

    /**
     * A native method that is implemented by the 'pdfhelper' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'pdfhelper' library on application startup.
        init {
            System.loadLibrary("pdfhelper")
        }
    }
}