package com.wangjie.kotlinexample.ui.database.provider.dao

import com.wangjie.kotlinexample.ui.database.provider.model.Person
import com.wangjie.rapidorm.core.dao.BaseDaoImpl

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */

public interface PersonDao {

}


public class PersonDaoImpl : BaseDaoImpl<Person>(javaClass<Person>()) {

}

