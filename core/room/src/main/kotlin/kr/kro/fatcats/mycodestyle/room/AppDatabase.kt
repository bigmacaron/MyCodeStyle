package kr.kro.fatcats.mycodestyle.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.kro.fatcats.mycodestyle.room.converters.Converters
import kr.kro.fatcats.mycodestyle.room.dao.ExcuseDao
import kr.kro.fatcats.mycodestyle.room.entitys.ExcuseEntity

@Database(entities = [ExcuseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun excuseDao(): ExcuseDao
}

//@Database(
//    entities = [ExcuseEntity::class],
//    version = 1,
//)
//@TypeConverters(Converters::class)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun excuseDao(): ExcuseDao
//
//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//        const val ROOM_NAME = "notToday"
//
//        private val roomDatabaseCallback = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                Executors.newSingleThreadExecutor().execute {
//                    instance?.let { database ->
//                        CoroutineScope(Dispatchers.IO).launch {
//                            database.excuseDao()
//                        }
//                    }
//                }
//            }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context, AppDatabase::class.java, ROOM_NAME)
//                .addCallback(roomDatabaseCallback)
//                .addMigrations()
//                .build()
//
//        private fun getDatabase(context: Context): AppDatabase =
//            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }
//
//        @JvmStatic
//        fun getExcuseDatabase(context: Context): ExcuseDao {
//            return getDatabase(context).excuseDao()
//        }
//
//    }
//
//}