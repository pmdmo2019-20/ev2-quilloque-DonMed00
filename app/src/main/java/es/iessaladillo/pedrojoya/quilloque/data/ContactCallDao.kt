package es.iessaladillo.pedrojoya.quilloque.data

import androidx.room.Dao
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall

@Dao
interface ContactCallDao {



    @Query("SELECT C.id AS callId, C.phoneNumber AS phoneNumber, C.type AS type, \n" +
            "C.date AS date, C.time AS time, T.id AS contactId, T.name AS contactName \n" +
            "FROM Call AS C LEFT JOIN Contact AS T ON C.phoneNumber = T.phoneNumber \n" +
            "ORDER BY C.id DESC LIMIT 20")
    fun queryRecentCalls(): List<ContactWithCall>
}