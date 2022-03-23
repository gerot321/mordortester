package com.mordor.app;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class IntBooleanTypeAdapter extends TypeAdapter<Boolean> {
	@Override
	public void write(JsonWriter out, Boolean value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else if (value) {
			out.value(1);
		} else {
			out.value(0);
		}
	}

	@Override
	public Boolean read(JsonReader in) throws IOException {
		JsonToken peek = in.peek();
		switch (peek) {
			case BOOLEAN:
				return in.nextBoolean();
			case NUMBER:
				return in.nextInt() == 1 ? true : false;
			case STRING:
				return toBoolean(in.nextString());
			default:
				return false;
		}
	}

	public static boolean toBoolean(String name) {
		if (TextUtils.isEmpty(name)){
			return false;
		}else{
			if (name.equalsIgnoreCase("true")){
				return true;
			}else if (name.equalsIgnoreCase("false")){
				return false;
			}else if (name.equals("1")){
				return true;
			}else if (name.equals("0")){
				return false;
			}
		}
		return false;
	}
}