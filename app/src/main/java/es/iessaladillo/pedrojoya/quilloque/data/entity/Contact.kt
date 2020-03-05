package es.iessaladillo.pedrojoya.quilloque.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(
            name = "CONTACT_NAME_UNIQUE",
            value = ["name"],
            unique = true
        ),
        Index(
            name = "CONTACT_PHONENUMBER_UNIQUE",
            value = ["phoneNumber"],
            unique = true
        )
    ]
)
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    val phoneNumber: String
)