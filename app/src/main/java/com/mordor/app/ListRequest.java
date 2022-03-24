package com.mordor.app;

import lombok.Data;

@Data
public class ListRequest<BaseFilter, BaseOrder> {
    private int dataPerPage;
    private int pageCurrent;
    private BaseOrder orderBy;
    private BaseFilter filter;

    public ListRequest(int dataPerPage, int pageCurrent, BaseFilter filter ){
        this.dataPerPage = dataPerPage;
        this.pageCurrent = pageCurrent;
        this.filter = filter;
        this.orderBy = (BaseOrder) new EmptyObject();
    }

    public ListRequest(){
        this.dataPerPage = 99999;
        this.pageCurrent = 1;
        this.filter = (BaseFilter) new EmptyObject();
        this.orderBy = (BaseOrder) new EmptyObject();
    }

    public ListRequest(int dataPerPage, int pageCurrent, BaseFilter filter, BaseOrder orderBy ){
        this.dataPerPage = dataPerPage;
        this.pageCurrent = pageCurrent;
        this.filter = filter;
        this.orderBy = orderBy;
    }
}
