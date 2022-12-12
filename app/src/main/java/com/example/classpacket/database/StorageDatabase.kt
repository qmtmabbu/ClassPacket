package com.example.classpacket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clsspacket.database.StorageDao

@Database(entities = [Storage::class], version = 1, exportSchema = false)
abstract class StorageDatabase : RoomDatabase(){

    abstract fun storageDao(): StorageDao


    companion object{
        @Volatile
        private var INSTANCE: StorageDatabase? = null

        fun getDatabase(context: Context): StorageDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StorageDatabase::class.java,
                    "storage_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}