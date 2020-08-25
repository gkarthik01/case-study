package com.target.dealbrowserpoc.dealbrowser.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.target.dealbrowserpoc.dealbrowser.data.dao.DealDao
import com.target.dealbrowserpoc.dealbrowser.data.entities.DealEntity

@Database(entities = [DealEntity::class ], version = 1, exportSchema = false)
abstract class DealDatabase : RoomDatabase(){
    abstract fun dealDao(): DealDao
}