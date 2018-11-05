package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.ItemsMapper;
import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.pojo.ItemsExample;
import cn.itcast.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {


    @Autowired
    private ItemsMapper itemsMapper;



    @Override
    public List<Items> getItemsList() {

        ItemsExample itemsExample = new ItemsExample();
        List<Items> items = itemsMapper.selectByExample(itemsExample);

        return items;
    }

    @Override
    public Items findAllById(Integer id) {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public void updateItems(Items items) {
        itemsMapper.updateByPrimaryKeySelective(items);
    }

    @Override
    public void insert(Items items) {
        for (int i = 0; i < 100; i++) {
            items.setName("小花" + i);
            items.setPrice(1990.00 + i);
            items.setPic("手机" + i);
            itemsMapper.insert(items);

        }

    }

    @Override
    public void bachInsert(List<Items> items) {

       /* for (int i = 0; i < 100; i++) {
            items.setName("小花" + i);
            items.setPrice(1990.00 + i);
            items.setPic("手机" + i);
            itemsMapper.bachInsert(items);

        }*/

    }

}
