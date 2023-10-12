package com.fikrisandi.local.converter

import androidx.room.TypeConverter
import com.fikrisandi.framework.extension.fromJson
import com.fikrisandi.framework.extension.toJson
import com.fikrisandi.framework.room.converter.BaseConverter

class IntegerConverter : BaseConverter<List<Int?>?> {

    @TypeConverter
    override fun objToString(data: List<Int?>?): String {
        return data.orEmpty().toJson()
    }

    @TypeConverter
    override fun stringToObj(data: String): List<Int?>? {
        return data.fromJson()
    }
}