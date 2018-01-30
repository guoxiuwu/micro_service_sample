package com.ebao.gs.sp.rc.common.utils

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class Pagination<T> implements Serializable {

    boolean pageable = true
    int countPerPage = 10
    int pageIndex = 1
    int pageCount = 0
    long totalRecords = 0
    Sort[] sort = []

    static class Sort {
        String sortBy
        String direction = SortDirection.ASC.toString()
    }

    static enum SortDirection {
        ASC, DESC
    }

    @JsonIgnore
    int getStartIndex() {
        int i = countPerPage * (pageIndex - 1)
        i < 0 ? 0 : i
    }

    int getPageCount() {
        if (0 == totalRecords % countPerPage) {
            return totalRecords / countPerPage
        } else {
            return (totalRecords / countPerPage) + 1
        }
    }

    List<T> items = []

}
