package es.iessaladillo.pedrojoya.quilloque.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact
import kotlin.concurrent.thread

@Database(
    entities = [Call::class, Contact::class],
    version = 1,
    exportSchema = true
)
abstract class CallContactsDatabase : RoomDatabase() {

    abstract val callDao : CallDao
    abstract val contactDao: ContactDao
    abstract val contactCallDao: ContactCallDao


    companion object {

        @Volatile
        private var INSTANCE: CallContactsDatabase? = null

        fun getInstance(context: Context): CallContactsDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CallContactsDatabase::class.java,
                            "callContacts_database"
                        ).addCallback(object : Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                thread {
                                    INSTANCE!!.contactDao.insertContact(Contact(0,"Adrian","956001122"))
                                    INSTANCE!!.contactDao.insertContact(Contact(0,"Jose","78957587"))
                                    INSTANCE!!.contactDao.insertContact(Contact(0,"Jaime","757572379"))
                                    INSTANCE!!.callDao.insertCall(Call(0,"56598789","made","10/02/2020","20:45"))
                                    INSTANCE!!.callDao.insertCall(Call(0,"565989","made","10/03/2020","20:02"))
                                    INSTANCE!!.callDao.insertCall(Call(0,"5650202989","received","11/02/2020","15:02"))
                                    INSTANCE!!.callDao.insertCall(Call(0,"956001122","received","11/02/2020","15:02"))
                                }


                            }
                        })
                            .allowMainThreadQueries()
                            .build()


                    }
                }
            }
            return INSTANCE!!

        }
    }
}