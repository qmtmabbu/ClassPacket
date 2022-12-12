package com.example.classpacket.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage_table")
data class Storage (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "Packet Type") val PacketType: Int?,
    @ColumnInfo(name = "Destination Address") val DestinationAddress: Int?,
    @ColumnInfo(name = "Source Address") val SourceAddress: Int?,
    @ColumnInfo(name = "Details") val Details: Int?)
