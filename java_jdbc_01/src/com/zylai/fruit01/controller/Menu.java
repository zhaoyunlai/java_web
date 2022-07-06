package com.zylai.fruit01.controller;

import com.zylai.fruit01.dao.FruitDAO;
import com.zylai.fruit01.dao.impl.FruitDAOImpl;
import com.zylai.fruit01.pojo.Fruit;

import java.util.List;
import java.util.Scanner;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/17:35
 * @Description:
 */
public class Menu {

    Scanner input = new Scanner(System.in);
    private FruitDAO fruitDAO = new FruitDAOImpl();

    public int showMainMenu(){
        System.out.println("=================欢迎使用水果库存系统=====================");
        System.out.println("1.查看水果库存列表");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("======================================================");
        System.out.print("请选择：");
        int res = input.nextInt();
        while(true){
            if(res<1||res>5){
                System.out.println("请在1~5当中进行选择！");
                System.out.print("请选择：");
                res = input.nextInt();
            }else{
                break;
            }
        }
        return res;
    }
//    显示所有水果库存
    public void showFruitList(){
        List<Fruit> fruitList = fruitDAO.getFruitList();
        System.out.println("------------------------------------------------------");
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注\t\t");
        if(fruitList==null||fruitList.size()<=0){
            System.out.println("对不起，库存为空");
        }else{
            for (Fruit fruit : fruitList) {
                System.out.println(fruit);
            }
        }
        System.out.println("------------------------------------------------------");
    }

//    添加水果库存信息 -- 业务方法
    public void addFruit(){
        System.out.print("请输入水果名称：");
        String fname = input.next();
//       判断是添加库存还是添加一个新水果
        Fruit fruitByName = fruitDAO.getFruitByName(fname);
        if(fruitByName==null){//说明库存中没有这个水果，直接添加
            System.out.print("请输入水果单价：");
            int price = input.nextInt();
            System.out.print("请输入水果库存量：");
            int fcount = input.nextInt();
            System.out.print("请输入水果备注：");
            String remark = input.next();
            fruitByName = new Fruit(0,fname,price,fcount,remark);
//            调用dao
            fruitDAO.addFruit(fruitByName);
        }else{
            System.out.print("请输入追加的库存量：");
            int fcount = input.nextInt();
            fruitByName.setFcount(fruitByName.getFcount()+fcount);
            fruitDAO.updateFruit(fruitByName);
        }
    }
//    查看特定水果库存信息
    public void showFruitInfo(){
        System.out.print("请输入水果名称：");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByName(fname);
        if(fruit == null){
            System.out.println("对不起，没有找到指定的水果库存记录");
        }else{
            System.out.println("------------------------------------------------------");
            System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注\t\t");
            System.out.println(fruit);
            System.out.println("------------------------------------------------------");
        }
    }

    public void deleteFruit(){
        System.out.print("请输入水果名称：");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByName(fname);
        if(fruit==null){
            System.out.println("对不起，没有找到要下架的水果信息");
        }else{
            System.out.print("是否下架？（Y/N）");
            String str = input.next();
            if("y".equalsIgnoreCase(str)){
                boolean b = fruitDAO.deleteFruitByName(fname);
                if(b){
                    System.out.println("下架成功！");
                }
            }
        }
    }
//    退出
    public boolean exit(){
        do{
            System.out.print("是否确认退出？(Y/N):");
            String res = input.next();
            if("y".equalsIgnoreCase(res)){
                return true;
            }else if("n".equalsIgnoreCase(res)){
                return false;
            }
        }while(true);

    }
}
