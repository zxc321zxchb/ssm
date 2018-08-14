package com.itheima.springmvc.po;

import java.util.List;

public class QueryVo {
    private Items items;
    private List<Items> itemsList;

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
