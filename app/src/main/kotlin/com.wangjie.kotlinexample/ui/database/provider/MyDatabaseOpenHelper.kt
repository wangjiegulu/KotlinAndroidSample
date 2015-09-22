package com.wangjie.kotlinexample.ui.database.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wangjie.rapidorm.core.connection.DatabaseProcessor
import com.wangjie.rapidorm.core.delegate.database.RapidORMDefaultSQLiteDatabaseDelegate

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
public class MyDatabaseOpenHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val rapidORMSQLiteDatabaseDelegate = RapidORMDefaultSQLiteDatabaseDelegate(db)
        val databaseProcessor = DatabaseProcessor.getInstance()
        databaseProcessor.initializeDatabase(rapidORMSQLiteDatabaseDelegate)

        for (tableClazz in databaseProcessor.getAllTableClass()) {
            databaseProcessor.createTable(rapidORMSQLiteDatabaseDelegate, tableClazz, true)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val rapidORMSQLiteDatabaseDelegate = RapidORMDefaultSQLiteDatabaseDelegate(db)
        val databaseProcessor = DatabaseProcessor.getInstance()
        databaseProcessor.initializeDatabase(rapidORMSQLiteDatabaseDelegate)

        // todo: dev only!!!!!
        databaseProcessor.dropAllTable(rapidORMSQLiteDatabaseDelegate)
        onCreate(db)
    }
}
