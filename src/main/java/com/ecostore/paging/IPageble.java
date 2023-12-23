package com.ecostore.paging;

import com.ecostore.sort.Sorter;

public interface IPageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();
}
