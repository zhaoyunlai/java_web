package com.zylai.myssm.basedao;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/10:56
 * @Description:
 */
public class DAOException extends RuntimeException{
    public DAOException(String msg){
        super(msg);
    }
}
