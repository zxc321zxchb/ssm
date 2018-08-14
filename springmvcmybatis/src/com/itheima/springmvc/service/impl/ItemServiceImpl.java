package com.itheima.springmvc.service.impl;

import com.itheima.springmvc.mapper.ItemsMapper;
import com.itheima.springmvc.po.Items;
import com.itheima.springmvc.po.QueryVo;
import com.itheima.springmvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> getItemsList() {
        List<Items> itemList = itemsMapper.getItemList();
        return itemList;
    }

    //根据id查询
    @Override
    public Items getItemsById(Integer id) {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public void updateItem(Items items) {
        itemsMapper.updateByPrimaryKeySelective(items);
    }

    @Override
    public QueryVo queryItem(Items items) {


        return null;
    }

}

