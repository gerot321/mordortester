package com.mordor.app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class Domain{
    @Getter @Setter
    public String name;
    @Getter @Setter
    public String status;
    @Getter @Setter
    public Site Site;
    @Getter @Setter
    public int Rank;
}
