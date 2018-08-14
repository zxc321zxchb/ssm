package com.itheima.springmvc.service;

import com.itheima.springmvc.po.Items;
import com.itheima.springmvc.po.QueryVo;

import java.util.List;

public interface ItemService {

	List<Items> getItemsList();

	//点击修改,根据id查询出要修改商品 的id
	Items getItemsById(Integer id);

	//修改
	void updateItem(Items items);

	//查询
	QueryVo queryItem(Items items);
}
