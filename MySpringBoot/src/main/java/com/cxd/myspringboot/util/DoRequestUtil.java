package com.cxd.myspringboot.util;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 发起http请求
 */

public class DoRequestUtil {
    /**
     * post带参数
     */
    public static String sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        //JSONPObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            //打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0; Windows NT 5.1;SV1)");
            //发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流(设置请求编码为UTF-8)
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            //发送请求
            out.print(params);
            //flush输出流的缓冲
            out.flush();
            //获取请求返回数据(设置返回数据编码UTF-8)
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //jsonObject = JSONPObject.fromObject(result);

        } catch (IOException e) {
            System.out.print("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
