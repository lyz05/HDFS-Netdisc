package com.elon33.netdisc.controller;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import com.elon33.netdisc.model.HdfsDAO;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        String local = context.getInitParameter("file-download");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"), "GB2312");
        System.out.println(filePath);
        JobConf conf = HdfsDAO.config();
        HdfsDAO hdfs = new HdfsDAO(conf);
        hdfs.download(filePath, local);

        // 从服务器下载文件到本地
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		FileInputStream in = new FileInputStream(local);  // 读取要下载的文件,保存到文件输入流
		OutputStream out = response.getOutputStream();  // 创建输出流
		byte buffer[] = new byte[1024];  // 创建缓冲区
		int len = 0;
		while ((len = in.read(buffer)) > 0) {  // 循环将输入流中的内容读取到缓冲区当中
			out.write(buffer, 0, len);  // 输出缓冲区的内容到浏览器，实现文件下载
		}
		in.close();  // 关闭文件输入流
		out.close();  // 关闭输出流

//		FileStatus[] list = hdfs.ls((String)session.getAttribute("currentPath"));
//		request.setAttribute("list", list);
//		request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
