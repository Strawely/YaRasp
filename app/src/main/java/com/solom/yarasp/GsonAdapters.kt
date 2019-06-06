package com.solom.yarasp

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.NumberFormatException

class DoubleTypeAdapter : TypeAdapter<Double>() {
    override fun write(out: JsonWriter?, value: Double?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(reader: JsonReader?): Double {
        if (reader == null) return .0
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return .0
        }
        val stringValue = reader.nextString()
        return try {
            stringValue.toDouble()
        } catch (e: NumberFormatException) {
            .0
        }
    }
}
/*
public class LongTypeAdapter extends TypeAdapter<Long>{
    @Override
    public Long read(JsonReader reader) throws IOException {

    }
    @Override
    public void write(JsonWriter writer, Long value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }
}*/
