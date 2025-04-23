package kr.kro.fatcats.mycodestyle.room.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.kro.fatcats.mycodestyle.room.AppDatabase
import kr.kro.fatcats.mycodestyle.room.dao.ExcuseDao
import kr.kro.fatcats.mycodestyle.room.util.InitData
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    val TAG = "DatabaseModule"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        initData: InitData
    ): AppDatabase {
        var database: AppDatabase? = null
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "notToday")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(dbSupport: SupportSQLiteDatabase) {
                    super.onCreate(dbSupport)
                    CoroutineScope(Dispatchers.IO).launch {
                        database?.let { db ->
                            val data = initData.insertDefaultExcuses().also { Log.d(TAG, "onCreate: provideDatabase $it") }
                            db.excuseDao().insertAll(data)
                        }
                    }
                }
            })
            .build()
        return db
    }

    @Provides
    fun provideExcuseDao(db: AppDatabase): ExcuseDao = db.excuseDao()

}