package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDAO {

    @Query("SELECT * FROM driver_table ORDER BY full_name ASC")
    fun getAllDrivers(): Flow<List<OpenF1DriverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDriver(driverEntity: OpenF1DriverEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrivers(drivers: List<OpenF1DriverEntity>)

    @Query("DELETE FROM driver_table")
    fun deleteAllDrivers()

    @Query("SELECT * FROM driver_table WHERE driver_number = :driverNumber LIMIT 1")
    fun getDriverByNumber(driverNumber: Int): OpenF1DriverEntity?

    @Query("SELECT * FROM driver_table WHERE team_name = :teamName")
    fun getDriversByTeam(teamName: String): Flow<List<OpenF1DriverEntity>>

    @Update
    fun updateDriver(driverEntity: OpenF1DriverEntity)

    @Delete
    fun deleteDriver(driverEntity: OpenF1DriverEntity)
}
