package cn.itcast.ssm.service;

import cn.itcast.ssm.pojo.Items;

import java.util.List;

public interface ItemsService {
    List<Items> getItemsList();

    Items findAllById(Integer id);

    void updateItems(Items items);

    void insert(Items items);

    void bachInsert(List<Items> items);

}
