package com.itheima.springmvc.controller;

import com.itheima.springmvc.exception.CustomerException;
import com.itheima.springmvc.po.Items;
import com.itheima.springmvc.po.QueryVo;
import com.itheima.springmvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping("/item")
public class ItemController {

    /**
     * 查询所有
     */
    @Autowired
    private ItemService itemService;

    @RequestMapping("/itemList")
    public ModelAndView getItemsList() throws CustomerException {

        /*if (true) {
            throw new CustomerException("自定义异常,哈哈哈哈...");
        }*/
        //int i = 1 / 0;

        List<Items> itemsList = itemService.getItemsList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemsList);
        modelAndView.setViewName("itemList");

        return modelAndView;
    }

    /**
     * 点击修改商品,根据id查询到商品的信息
     */
   /* @RequestMapping("/itemEdit")
    public String itemEdit(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session,
                               Model model){
        //从request请求域中获取id,并转成Integer
        Integer id = new Integer(request.getParameter("id"));

        Items items = itemService.getItemsById(id);

        model.addAttribute("item", items);

        return "editItem";
    }*/
    /*@RequestMapping("/itemEdit")
    public String EditItem(@RequestParam(value = "id", required = true) Integer id, Model model) {

        Items items = itemService.getItemsById(id);
        model.addAttribute("item", items);

        return "editItem";
    }*/

    /**
     *  restful风格
     */
    @RequestMapping("/itemEdit/id/{id}/name/{name}")
    public String EditItem(@PathVariable(value = "id") Integer iid, Model model) {

        Items items = itemService.getItemsById(iid);
        model.addAttribute("item", items);

        return "editItem";
    }

    /**
     * 修改,图片上传
     */
    @RequestMapping("/updateitem")
    public String updateItem(Items items,
                             MultipartFile multipartFile) throws IOException {
        //把图片保存到图片目录下
        //为每个文件生成一个新的文件名,并截出扩展名
        String picName = UUID.randomUUID().toString();

        //取扩展名,获取原图片文件名multipart.getOriginalFilename()
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = originalFilename.substring(originalFilename.lastIndexOf("."));

        //保存文件
        File file = new File("c:\\temp\\images\\" + picName + fileName);
        multipartFile.transferTo(file);

        //文件名保存到数据库
        items.setPic(picName + fileName);

        itemService.updateItem(items);
        return "redirect:/itemEdit.action";
    }

    /*@RequestMapping("/updateitem")
    public String updateItem(QueryVo queryVo){

        itemService.updateItem(queryVo.getItems());
        //跳转页面
        return "forward:itemList.action";
    }*/

  /*  @RequestMapping("/updateitem")
    public String updateItem(QueryVo queryVo){

        itemService.updateItem(queryVo.getItems());
        //跳转页面
        return "redirect:itemList.action";
    }*/

    /**
     * 查询
     *
     * @param queryVo
     * @return
     */
    @RequestMapping("/queryitem")
    public String queryItem(QueryVo queryVo, String[] ids) {

        System.out.println(queryVo.getItems().getId());
        System.out.println(queryVo.getItems().getName());
        //System.out.println(ids.toString());

        return "success";
    }

    @RequestMapping("/itemList2")
    public void itemList2(HttpServletRequest request,
                          HttpServletResponse response
    ) throws ServletException, IOException {
      /*  //查询商品列表
        List<Items> itemsList = itemService.getItemsList();
        //想页面传递参数
        request.setAttribute("itemList", itemsList);
        //如果使用原始的的方式作业面跳转,必须给jsp完整路径
        request.getRequestDispatcher("/WEB-INF/jsp/itemList.jsp").forward(request, response);
*/
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        writer.write("{\"id\":\"123\"}");
    }

    /**
     * 文件上传,单独实现比较麻烦,可以修改商品的时候
     * 把图片信息一并上传
     */

    /**
     * json数据交互
     *
     * @requestBody 接收json数据, 并转换成pojo对象
     * @responsBody 响应json数据, 把java对象转换成josn并响应
     * 这里使用ajax发送
     */
    @RequestMapping("/jsontest.action")
    @ResponseBody
    public Items jsonTest(@RequestBody Items items) {

        return items;
    }
}

