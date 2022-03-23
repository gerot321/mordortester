package com.mordor.app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ListResponse<T>{
    @SerializedName("countTotal")
    @Getter @Setter
    public int countTotal;
    @SerializedName("pageTotal")
    @Getter @Setter
    public int pageTotal;
    @SerializedName("pageCurrent")
    @Getter @Setter
    public int pageCurrent;
    @SerializedName("dataPerPage")
    @Getter @Setter
    public int dataPerPage;
    @SerializedName("list")
    @Getter @Setter
    public List<T> list;
}
