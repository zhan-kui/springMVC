package com.atguigu.rest.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZK
 * @create 2021-12-27 下午 2:46
 *
 * 通用的返回Json数据的类
 */
public class Msg {
    //状态码   100-成功    200-失败
    private int code;
    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }

//身份证校验

    public static boolean regexCheckIdentityCard(String cardNo) {
        if (null == cardNo)
            return false;
        cardNo = cardNo.trim();
        if (15 == cardNo.length() || 18 == cardNo.length()) {
            /*		15位旧居民身份证：
             * 	    "\\d{8}"                  1~6位分别代表省市县，这里不取字典表校验，只校验是否数字。
             * 								  7~8位代表年份后两位数字
             * 		"(0[1-9]|1[12])"          9~10位代表月份，01~12月
             * 		"(0[1-9]|[12]\\d|3[01])"  11~12位代表日期，1~31日
             * 		"\\d{3}"                  13~15位为三位顺序号
             *
             *     	18位新居民身份证：
             * 	    "\\d{6}"                  1~6位分别代表省市县，这里不取字典表校验，只校验是否数字。
             * 		"(18|19|20)\\d{2}"        7~10位代表年份，前两位18、19、20即19世纪、20世纪、21世纪，后两位数字。
             * 		中国寿星之首：阿丽米罕·色依提，女，1886年6月25日出生于新疆疏勒县，现年134岁，身份证起始日期在19世纪
             * 		"(0[1-9]|1[12])"          11~12位代表月份，01~12月
             * 		"(0[1-9]|[12]\\d|3[01])"  13~14位代表日期，1~31日
             * 		"\\d{3}"                  15~17位为三位顺序号
             * 		"(\\d|X|x)"               18位为校验位数字，允许字母x和X
             *
             * 		正则表达式合并为：
             * 		^(\\d{6}(18|19|20)\\d{2}(0[1-9]|1[12])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x))|(\\d{8}(0[1-9]|1[12])(0[1-9]|[12]\\d|3[01])\\d{3})$
             * */
            Pattern pattern = Pattern.compile("^(\\d{6}(18|19|20)\\d{2}(0[1-9]|1[12])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x))|(\\d{8}(0[1-9]|1[12])(0[1-9]|[12]\\d|3[01])\\d{3})$");
            Matcher m = pattern.matcher(cardNo);
            return (m.matches()) ? true : false;
        } else {
            return false;
        }

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

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }


}
