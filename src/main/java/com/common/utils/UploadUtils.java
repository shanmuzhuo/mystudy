package com.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author zzx
 * 文件传类，主要依赖于apache.commons.fileupload
 */
public class UploadUtils {

	public static boolean uploadFile(HttpServletRequest req, String savePath1) throws Exception {
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = req.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdir();
		}
		// 消息提示
		String message = "";

		// 使用Apache文件上传组件处理文件上传步骤：
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2.设置缓存
		factory.setSizeThreshold(20 * 1024);
		// 3.创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		//upload.setHeaderEncoding("UTF-8");
		// 判断上传的数据是否是表单数据
		if (!ServletFileUpload.isMultipartContent(req)) {
			boolean sa = ServletFileUpload.isMultipartContent(req);
			System.out.println(sa);

			// 按照传统方式获取数据
			return false;
		}
		// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		List<FileItem> list = upload.parseRequest(req); //由于已经在配置文件当中使用了解析器，所以在解析一边的时候就会得到空值。
		
		for (FileItem fileItem : list) {
			// 如果fileItem是普通form类型
			if (fileItem.isFormField()) {
				String name = fileItem.getFieldName(); // 获取文件名
				// 解决普通输入项的数据的中文乱码问题
				String value = fileItem.getString("UTF-8");
				// value = new String(value.getBytes("iso8859-1"),"UTF-8");
				System.out.println(name + "=" + value);
			} else if (fileItem.getSize() > 0) {
				// 如果fileItem中封装的是上传文件,且文件大于0
				// 得到上传的文件名称，
				String filename = fileItem.getName();
				System.out.println(filename);
				if (filename == null || filename.trim().equals("")) {
					continue;
				}
				// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
				// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
				filename = filename.substring(filename.lastIndexOf("\\") + 1);
				// 获取item中的上传文件的输入流
				InputStream in = fileItem.getInputStream();
				// 创建一个文件输出流
				FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
				// 创建一个缓冲区
				byte buffer[] = new byte[1024];
				// 判断输入流中的数据是否已经读完的标识
				int len = 0;
				// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while ((len = in.read(buffer)) > 0) {
					// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					out.write(buffer, 0, len);
				}
				in.close();
				out.close();// 关闭流
				fileItem.delete(); // 删除临时文件
			}
		}
		return true;
	}
}
