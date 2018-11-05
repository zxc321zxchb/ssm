package cn.itcast.ssm.controller;

import cn.itcast.ssm.pojo.Items;
import cn.itcast.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemsContrller {



    @Autowired
    private ItemsService itemsService;


    /**
     * 查询所有
     * @param model
     * @return
     */
    @RequestMapping("/queryItems")
    public String getItemsList(Model model){

        System.out.println("进入到queryItems......................");

        List<Items> itemsList = itemsService.getItemsList();

        model.addAttribute("items", itemsList);
        return "itemList";
    }

    /**
     *  根据id查询
     */
    @RequestMapping("/editItems")
    public String findAllById(Integer id, Model model){

        Items items = itemsService.findAllById(id);
        model.addAttribute("items", items);

        return "editItems";
    }

    @RequestMapping("/updateItems")
    public String updateItem(Items items,
                             MultipartFile multipartFile) throws IOException {
        //把图片保存到图片目录下
        //为每个文件生成一个新的文件名,并截出扩展名
        String picName = UUID.randomUUID().toString();

        //取扩展名,获取原图片文件名multipart.getOriginalFilename()
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = originalFilename.substring(originalFilename.lastIndexOf("."));

        //保存文件
        File file = new File("c:\\upload\\" + picName + fileName);
        multipartFile.transferTo(file);

        //文件名保存到数据库
        items.setPic(picName + fileName);

        itemsService.updateItems(items);
        return "redirect:/items/editItems.action";
    }


}
