package com.fikrisandi.local.converter

import androidx.room.TypeConverter
import com.fikrisandi.framework.extension.fromJson
import com.fikrisandi.framework.extension.toJson
import com.fikrisandi.framework.room.converter.BaseConverter
import com.fikrisandi.model.remote.movie.Trailer

class TrailerConverter: BaseConverter<Trailer?> {
    @TypeConverter
    override fun objToString(data: Trailer?): String {
        return data.toJson()
    }

    @TypeConverter
    override fun stringToObj(data: String): Trailer? {
        return data.fromJson<Trailer>()
    }

}