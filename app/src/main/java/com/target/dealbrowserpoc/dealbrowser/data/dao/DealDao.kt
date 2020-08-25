package com.target.dealbrowserpoc.dealbrowser.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.target.dealbrowserpoc.dealbrowser.data.entities.DealEntity
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DealDao {
    @Query("SELECT * FROM deals where id = :dealId")
    fun getDealDetails(dealId: String) : Single<DealEntity?>

    @Insert(onConflict = REPLACE)
    fun insertAll(deals: List<DealEntity>?) : Completable
}