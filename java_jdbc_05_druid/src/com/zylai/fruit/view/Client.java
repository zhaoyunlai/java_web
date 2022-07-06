package com.zylai.fruit.view;

import com.zylai.fruit.controller.Menu;
import com.zylai.fruit.controller.UserController;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/17:36
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean flag = false;
        while(!flag){
            //        调用主菜单的方法
            int slt = menu.showMainMenu();
            switch(slt){
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.deleteFruit();
                    break;
                case 5:
                    flag = menu.exit();
                    break;
            }
        }
        System.out.println("谢谢使用");

    }
}
