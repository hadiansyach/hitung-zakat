package com.d3if3032.hitungzakat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ZakatEntity::class], version = 1, exportSchema = false)
abstract class ZakatDb : RoomDatabase() {

    abstract val dao: ZakatDao

    companion object {
        @Volatile
        private var INSTANCE: ZakatDb? = null

        fun getInstance(context: Context): ZakatDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ZakatDb::class.java,
                        "bmi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
