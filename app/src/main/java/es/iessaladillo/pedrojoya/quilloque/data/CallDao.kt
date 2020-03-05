package es.iessaladillo.pedrojoya.quilloque.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call

@Dao
interface CallDao {

    @Insert
    fun insertCall(call: Call)

    @Delete
    fun deleteCall(call : Call)

    @Query("SELECT * FROM Call WHERE id = :callId")
    fun queryCall(callId: Long): Call
}