package com.sushanthande.gocorona.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *Created by Sushant Hande on 25-03-2020
 */
@Database(entities = [], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "GoCorona"
    }

}