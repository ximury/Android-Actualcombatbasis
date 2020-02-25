/**
 * 
 */
package com.back.base.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author real
 * 
 */
public class HttpUtils {
    
    private static Logger logger = Logger.getLogger(HttpUtils.class);
    
    public static final String SUCCESS = "操作成功!";
    public static final String FAIL = "操作失败!";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    

    public static void printInfo(String msg, HttpServletResponse response)  throws IOException {
//         resp.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(msg);
        writer.flush();
    }
    
    /**
     * 设置让浏览器弹出下载对话框的Header.
     * 
     * @param fileName 下载后的文件名.
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            //中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader(CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
            // doing nothing...
        }
    }
    
    /**
     * 设置Gzip Header并返回GZIPOutputStream.
     */
    public static OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Vary", "Accept-Encoding");
        return new GZIPOutputStream(response.getOutputStream());
    }


}
