package es.iessaladillo.pedrojoya.quilloque.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Call(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var phoneNumber: String,
    val type: String,
    val date: String,
    val time: String
    )