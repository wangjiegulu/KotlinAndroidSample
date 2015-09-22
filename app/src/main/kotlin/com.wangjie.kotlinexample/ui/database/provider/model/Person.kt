package com.wangjie.kotlinexample.ui.database.provider.model

import com.wangjie.rapidorm.annotations.Column
import com.wangjie.rapidorm.annotations.Table
import java.io.Serializable

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
@Table
public data class Person : Serializable{
    @Column(primaryKey = true)
    var id: Int? = null;
    @Column
    var name: String? = null;
    @Column
    var email: String? = null;
    @Column
    var student: Boolean? = null;

}
