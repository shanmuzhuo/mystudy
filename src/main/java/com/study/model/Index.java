package com.study.model;

import java.math.BigDecimal;

public class Index {
    private Integer id;

    private String indexname;

    private Integer pid;

    private BigDecimal singlelevel;

    private BigDecimal generallevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname == null ? null : indexname.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getSinglelevel() {
        return singlelevel;
    }

    public void setSinglelevel(BigDecimal singlelevel) {
        this.singlelevel = singlelevel;
    }

    public BigDecimal getGenerallevel() {
        return generallevel;
    }

    public void setGenerallevel(BigDecimal generallevel) {
        this.generallevel = generallevel;
    }
}