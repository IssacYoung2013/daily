package com.issac.spring.ssm.controller;

import com.issac.spring.ssm.exception.CustomException;
import com.issac.spring.ssm.po.Item;
import com.issac.spring.ssm.po.ItemQueryVo;
import com.issac.spring.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: ywy
 * @date: 2019-10-30
 * @desc:
 */
@Controller
@RequestMapping(produces = "application/json;charset=utf8")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("queryItem")
    public ModelAndView queryItem() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("items", itemService.queryItemList());
//        mv.setViewName("/WEB-INF/jsp/item-list.jsp");
        mv.setViewName("item-list");
        return mv;
    }

    @RequestMapping("queryItem1")
    public String queryItem(HttpServletRequest request,
                            Model model) throws CustomException {
        List<Item> items = itemService.queryItemList();
        if (items.size() < 10) {
            throw new CustomException("自定义异常类");
        }
        // 使用request api
//        request.setAttribute("items",items);
        // 使用model api
        model.addAttribute("items", items);
        System.out.println("debug");
//        return "item-list";
//        return "redirect:testRedirect";
        return "forward:testForward";
    }

    /**
     * 请求重定向
     *
     * @return
     */
    @RequestMapping("testRedirect")
    public String testRedirect(HttpServletRequest request) {
        if (request.getAttribute("items") != null) {
            System.out.println("Redirect get request data " + request.getAttribute("items"));
        } else {
            System.out.println("Redirect cannot get request data");
        }
        // ModelAndView 实际上就是视图转发
        return "";
    }

    /**
     * 转发
     *
     * @return
     */
    @RequestMapping("testForward")
    public String testForward(HttpServletRequest request) {
        if (request.getAttribute("items") != null) {
            System.out.println("Forward get request data " + request.getAttribute("items"));
        } else {
            System.out.println("Forward cannot get request data");
        }
        return "";
    }

    @RequestMapping("queryItemById")
    @ResponseBody
    public Item queryItemById() {
        return itemService.queryItemById(2);
    }

    @RequestMapping(value = "testReturnString",
            // 设置响应体编码
            produces = "application/json;charset=utf8"
            // 设置请求体编码
//            ,consumes = ""
            , method = RequestMethod.GET
    )
    @ResponseBody
    public String testReturnString() {
        return "华为1";
    }

    @RequestMapping(value = "testReturnString",
            // 设置响应体编码
            produces = "application/json;charset=utf8"
            // 设置请求体编码
//            ,consumes = ""
            , method = RequestMethod.POST
    )
    @ResponseBody
    public String testReturnString2() {
        return "华为2";
    }

    @RequestMapping(value = "findItem")
    @ResponseBody
    public String findItem(Integer id) {
        return "接收的请求参数：" + id;
    }

    /**
     * RequestParam 相当于 Request.getParameter
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "findItem2")
    @ResponseBody
    public String findItem2(@RequestParam(value = "itemid"
            , required = false, defaultValue = "3") Integer id) {
        return "接收的请求参数：" + id;
    }

    @RequestMapping(value = "findItem3")
    @ResponseBody
    public Item findItem3(@RequestParam(value = "id"
            , required = false, defaultValue = "3") Integer id) {
        return itemService.queryItemById(id);
    }

    @RequestMapping("findItem4/{id}")
    @ResponseBody
    public Item findItem4(@PathVariable Integer id) {
        return itemService.queryItemById(id);
    }

    /**
     * updateItem?id=1&name=iphone&price=100
     *
     * @return
     */
    @RequestMapping(value = "updateItem", method = RequestMethod.POST)
    @ResponseBody
    public Item updateItem(Integer id, String name, Float price, Item item) {
        return item;
    }

    /**
     * queryItem3?item.id=1&item.name=iphone&item.price=100
     *
     * @param itemQueryVo
     * @return
     */
    @RequestMapping("queryItem3")
    @ResponseBody
    public Item queryItem3(ItemQueryVo itemQueryVo) {
        return itemQueryVo.getItem();
    }

    /**
     * queryItem4?id=1&id=2&id=3
     *
     * @param id
     * @return
     */
    @RequestMapping("queryItem4")
    @ResponseBody
    public String[] queryItem4(String[] id) {
        return id;
    }

    /**
     * queryItem5?items[0].id=1&items[0].name=iphone&items[1].id=2&items[1].name=huawei
     *
     * @param itemQueryVo
     * @return
     */
    @RequestMapping("queryItem5")
    @ResponseBody
    public ItemQueryVo queryItem5(ItemQueryVo itemQueryVo) {
        return itemQueryVo;
    }

    /**
     * queryItem6?items[0].id=1&items[0].name=iphone&items[1].id=2&items[1].name=huawei
     *
     * @param itemQueryVo
     * @return
     */
    @RequestMapping("queryItem6")
    public String queryItem6(ItemQueryVo itemQueryVo, Model model) {
        model.addAttribute("items", itemQueryVo.getItems());
        return "item-list";
    }

    /**
     * saveItem?date=2019-10-31
     *
     * @return
     */
    @RequestMapping("saveItem")
    @ResponseBody
    public Date saveItem(Date date) {
        return date;
    }

    @RequestMapping("showEdit")
    public String showEdit(Integer id, Model model) {
        Item item = itemService.queryItemById(id);
        model.addAttribute("item", item);
        return "item-edit";
    }

    @RequestMapping(value = "updateItem1", method = RequestMethod.POST)
    @ResponseBody
    public Item updateItem1(Integer id, String name, Float price
            , Item item, MultipartFile pictureFile) throws IOException {
        System.out.println(pictureFile);
        if (pictureFile != null) {
            String originalFilename = pictureFile.getOriginalFilename();
            if (!StringUtils.isEmpty(originalFilename)) {
                String extName =
                        originalFilename.substring(originalFilename.lastIndexOf('.'));

                String newFileName = UUID.randomUUID().toString() + extName;
                String baseDir = "/Users/Issac/workspaces/github/upload/";
                File dirFile = new File(baseDir);
                if(!dirFile.exists()) {
                    dirFile.mkdir();
                }
                // 文件复制到目标路径
                pictureFile.transferTo(new File(baseDir + newFileName));
                // 保存文件路径
                item.setPic(newFileName);
            }
        }
        // 商品修改
        itemService.updateItem(item);
        return item;
    }

}
