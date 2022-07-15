package com.zylai.myssm.myspringmvc;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/18:12
 * @Description: 页面控制器，就是保证所有的页面访问都通过servlet，这样才能渲染themeleaf
 */
public class PageController {

    public String page(String page){
        return page;
    }
}
