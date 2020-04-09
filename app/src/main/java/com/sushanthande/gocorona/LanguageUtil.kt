package com.sushanthande.gocorona

/**
 *Created by Sushant Hande on 25-03-2020
 */
class LanguageUtil(val language: String) {

    companion object {
        const val ENGLISH = "English"
        const val HINDI = "हिंदी"
        const val MARATHI = "मराठी"
    }

    enum class LanguagePrefix(val language: String) {
        ENGLISH("en"), HINDI("hi"), MARATHI("mr")
    }
}
