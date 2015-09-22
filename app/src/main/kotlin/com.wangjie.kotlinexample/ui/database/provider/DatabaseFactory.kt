package com.wangjie.kotlinexample.ui.database.provider

import com.wangjie.androidbucket.application.ABApplication
import com.wangjie.kotlinexample.ui.database.provider.model.Person
import com.wangjie.rapidorm.core.connection.RapidORMConnection
import com.wangjie.rapidorm.core.delegate.openhelper.RapidORMDefaultSQLiteOpenHelperDelegate

import java.util.ArrayList

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
object DatabaseFactory : RapidORMConnection<RapidORMDefaultSQLiteOpenHelperDelegate>() {
    public val DB_NAME: String = "kotlin_android_sample.db"
    private val DB_VERSION = 1

    fun resetDatabase(): Boolean{
        return resetDatabase(DB_NAME);
    }

    override fun resetDatabaseIfCrash(): Boolean {
        return resetDatabase(DB_NAME)
    }

    override fun getRapidORMDatabaseOpenHelper(databaseName: String): RapidORMDefaultSQLiteOpenHelperDelegate {
        return RapidORMDefaultSQLiteOpenHelperDelegate(MyDatabaseOpenHelper(ABApplication.getInstance(), DB_NAME, DB_VERSION))
    }

    override fun registerAllTableClass(): List<Class<*>> {
        val list = ArrayList<Class<*>>()
        list.add(javaClass<Person>())
        return list
    }

}
