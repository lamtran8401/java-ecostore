package com.ecostore.paging;

import com.ecostore.sort.Sorter;

public class PageRequest implements IPageble {

    private Integer page;
    private Integer maxPageItems;
    private Sorter sorter;

    public PageRequest(int page, int maxPageItems, Sorter sorter) {
        this.page = page;
        this.maxPageItems = maxPageItems;
        this.sorter = sorter;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxPageItems != null)
            return maxPageItems * (page - 1);
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItems;
    }

    @Override
    public Sorter getSorter() {
        if (this.sorter != null)
            return this.sorter;
        return null;
    }
}
