package com.bdqn.ssm.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用Json返回类
 *
 * @author 贺威
 * @create 2018-10-14 15:26
 */
public class Mas {

    //返回 状态码 100-成功 200-失败
    private int code;

    //mas 提示信息
    private String msg;

    //用户返回给浏览器的数据
    private Map<String,Object> map=new HashMap<String,Object>();

    //成功
    public static  Mas success(){
        Mas result=new Mas();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }
    public static  Mas fail(){
        Mas result=new Mas();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }
    public Mas add(String Key,Object o){
        this.map.put(Key, o);
        return  this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
