package com.java8.stream;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: LinSong
 * @Date: 2018/12/28 16:55
 */
public class Test3 {

    public static void main(String[] args) {
    	
    	System.out.println(StringUtils.isNotBlank(""));
    	
     /*   1. public static boolean isEmpty(String str)
        判断某字符串是否为空，为空的标准是str==null或str.length()==0
        下面是StringUtils判断是否为空的示例：*/
      /*  StringUtils.isEmpty(null) = true
        StringUtils.isEmpty("") = true
        StringUtils.isEmpty(" ") = false //注意在StringUtils中空格作非空处理
        StringUtils.isEmpty(" ") = false
        StringUtils.isEmpty("bob") = false
        StringUtils.isEmpty(" bob ") = false


        2. public static boolean isNotEmpty(String str)
        判断某字符串是否非空，等于!isEmpty(String str)
        下面是示例：
        StringUtils.isNotEmpty(null) = false
        StringUtils.isNotEmpty("") = false
        StringUtils.isNotEmpty(" ") = true
        StringUtils.isNotEmpty(" ") = true
        StringUtils.isNotEmpty("bob") = true
        StringUtils.isNotEmpty(" bob ") = true


        3. public static boolean isBlank(String str)
        判断某字符串是否为空或长度为0或由空白符(whitespace)构成
        下面是示例：
        StringUtils.isBlank(null) = true
        StringUtils.isBlank("") = true
        StringUtils.isBlank(" ") = true
        StringUtils.isBlank(" ") = true
        StringUtils.isBlank("\t \n \f \r") = true //对于制表符、换行符、换页符和回车符StringUtils.isBlank()均识为空白符
        StringUtils.isBlank("\b") = false //"\b"为单词边界符
        StringUtils.isBlank("bob") = false
        StringUtils.isBlank(" bob ") = false


        4. public static boolean isNotBlank(String str)
        判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成，等于!isBlank(String str)
        下面是示例：
        StringUtils.isNotBlank(null) = false
        StringUtils.isNotBlank("") = false
        StringUtils.isNotBlank(" ") = false
        StringUtils.isNotBlank(" ") = false
        StringUtils.isNotBlank("\t \n \f \r") = false
        StringUtils.isNotBlank("\b") = true
        StringUtils.isNotBlank("bob") = true
        StringUtils.isNotBlank(" bob ") = true*/
    }
}
