package com.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNot;

/**
 * @author zzx 文件工具类 2017年8月27日09:56:34
 *
 */
public class FileUtils {

	/**
	 * @param sourcefile
	 *            源文件
	 * @param toName
	 *            目标名
	 * @throws IOException
	 */
	public static boolean renameTo(File sourcefile, String toName) {
		int eIndex = sourcefile.getAbsoluteFile().toString().lastIndexOf(".");
		File file = new File(
				sourcefile.getParentFile() + "\\" + toName);
		System.out.println("这个是util:" + sourcefile.getParentFile() + "\\" + toName
				+ sourcefile.getAbsoluteFile().toString().substring(eIndex));
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println(file.getAbsoluteFile());
				// File tempfile = File.createTempFile("重命名",
				// sourcefile.getAbsoluteFile().toString().substring(eIndex),sourcefile.getParentFile());
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(sourcefile));
				String line = null;
				List<String> out = new ArrayList<String>();
				do {
					line = reader.readLine();
					if (isNotBlank(line)) { // 判断是否是空行
						out.add(line);
					}
				} while (line != null);

				reader.close();

				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < out.size(); i++) {
					writer.write(out.get(i));
					writer.newLine();
				}

				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			//sourcefile.delete();
		}
		sourcefile.delete();
		return true;
	}

	/**
	 * 判断文件是否未空行
	 * 
	 * @param line
	 * @return
	 */
	private static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * @param str
	 *            字符串是否为空的，为空返回true
	 * @return
	 */
	private static boolean isBlank(String str) {
		int strlen;
		if (null == str || (strlen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strlen; i++) {
			if (Character.isWhitespace(str.charAt(i)) == false) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		renameTo(new File("D:/app/abc/bbbb.txt"), "重命名" + System.currentTimeMillis());
	}
}
