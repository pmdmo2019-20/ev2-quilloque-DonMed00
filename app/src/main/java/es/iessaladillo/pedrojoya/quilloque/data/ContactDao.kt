package es.iessaladillo.pedrojoya.quilloque.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM Contact")
    fun queryAllContacts(): List<Contact>


    @Query("SELECT * FROM Contact WHERE id = :contactId")
    fun queryContact(contactId: Long): Contact

    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE name like :nameText")
    fun queryAllContactsSearch(nameText : String): List<Contact>

    @Delete
    fun deleteContact(contact: Contact)
}