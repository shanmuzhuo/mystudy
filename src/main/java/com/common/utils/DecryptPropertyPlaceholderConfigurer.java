package com.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Pattern encryptedPattern = Pattern.compile("Encrypted");
	 private static final String ENCRYPTED_PREFIX = "Encrypted:";
	    private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 重写父类方法，解密指定属性名对应的属性值
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptPropertyVal(propertyName)) {
			System.out.println("22222222222222222222222222222222222222");
			System.out.println(propertyValue);
			//final Matcher matcher = encryptedPattern.matcher(propertyValue);
			if(propertyValue.contains("Encrypted")) { //如果是加密了，则解密
				System.out.println("我要开始解密了我要开始解密了我要开始解密了我要开始解密了我要开始解密了我要开始解密了");
				String decryptedPropValue = AESUtil.AESDecode(propertyValue.replaceAll("Encrypted:", ""));// 调用解密方法
				if (decryptedPropValue != null) {  //!=null说明正常
                    propertyValue = decryptedPropValue; //设置解决后的值
                } else {//说明解密失败
                   // logger.error("Decrypt " + propertyName + "=" + propertyValue + " error!");
                	System.out.println("Decrypt " + propertyName + "=" + propertyValue + " error!");
                }
			}else {
				return propertyValue;
			}
		} 
		return super.convertProperty(propertyName, propertyValue);  //将处理过的值传给父类继续处理;
	}

	/**
	 * 判断属性值是否需要解密，这里我约定需要解密的属性名用encrypt开头
	 * 
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptPropertyVal(String propertyName) {
		if (propertyName.startsWith("encrypt")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		super.postProcessBeanFactory(beanFactory); // 正常执行属性文件的加载

		for (Resource location : locations) {// 加载完后，遍历location，对properties进行加密
			try {
				final File file = location.getFile();
				if (file.isFile()) { // 如果是普通的文件
					if (file.canWrite()) { // 如果有写的方法
						System.out.println("开始加密");
						// 进行属性加密
						System.out.println("开始、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、");
						encrypt(file);
						file.setReadOnly(); // 加密后设置为只读文件
					}
				}
			} catch (Exception e) {
				System.out.println("加密文件出错"+e.getMessage());
			}

		}
	}

	/**
     * @param file
     * 对未加密的属性进行加密
     */
    private void encrypt(File file) {
    	 List<String> outputLine = new ArrayList<String>();   //定义输出行缓存

         boolean doEncrypt = false;     //是否加密属性文件标识

         BufferedReader bufferedReader = null;
         
         try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			do {
				line = bufferedReader.readLine(); //按行读取属性文件
				if(line != null) { //判断文件是否结束
					if(isNotBlank(line)) {  // 是否未空行
						line = line.trim(); 
						if(!line.startsWith("#")) { // 如果是非注释行
							int eIndex = line.indexOf("="); // 将属性名与值分离
							String key = line.substring(0, eIndex);
							String value = line.substring(eIndex+1);
							if(null != key && value != null && !value.contains("Encrypted")) {
								if(key.startsWith("encrypt")) { //需要加密字段
									final Matcher matcher = encryptedPattern.matcher(value); //如果是非加密段，则进行加密
									if(!matcher.matches()) {
										value = ENCRYPTED_PREFIX+AESUtil.AESEncode(value);
										line = key + "=" + value; //生成一行新的加密喘
										doEncrypt = true;
										System.out.println("加密了一个属性为："+key+"value:"+value);
									}
								}
							}
						}
					}
				}
				outputLine.add(line);
			} while (line != null);
		}catch (Exception e) {
			
		}finally {
            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                	System.out.println(e.getMessage());
//                   // logger.error(e.getMessage(), e);
//                }
            	System.out.println("aaaaaaaaaaaaa");
            }
        }
         
         if(doEncrypt) { //判断属性文件加密的标识
        	 BufferedWriter bufferedWriter = null;
             File tmpFile = null;
             try {
                 tmpFile = File.createTempFile("linshiwenjian", null, file.getParentFile());   //创建临时文件

                 /*
                 if (logger.isDebugEnabled()) {
                     logger.debug("Create tmp file '" + tmpFile.getAbsolutePath() + "'.");
                 }*/

                 bufferedWriter = new BufferedWriter(new FileWriter(tmpFile));
                 System.out.println("打印outputline");
                 //bufferedWriter.write("abcdefghijkrml");
                 for (int i = 0; i < outputLine.size()-1; i++) {
					System.out.println(outputLine.get(i)+"ooooop");
					bufferedWriter.write(outputLine.get(i));
					
					bufferedWriter.newLine();
				}
                 /*final Iterator<String> iterator = outputLine.iterator();
                 System.out.println("ec///xd");
                 while (iterator.hasNext()) {                           //将加密后内容写入临时文件
                     bufferedWriter.write(iterator.next());
                     bufferedWriter.newLine();
                 }*/
                 System.out.println("galg");
                 bufferedWriter.flush();
             } catch (IOException e) {
                 logger.error(e.getMessage(), e);
                 System.out.println("fasdfl;dfl;jalfjasdfjl;jfsdjl;jfasjfd");
             } finally {
                 if (bufferedWriter != null) {
                     try {
                         bufferedWriter.close();
                     } catch (IOException e) {
                         logger.error(e.getMessage(), e);
                     }
                 }
             }

             System.out.println("==============================================");
             System.out.println(file.getParentFile());
             System.out.println("==============================================");

     		int point = file.getAbsoluteFile().toString().indexOf(".");
     		File backupFile = new File(file.getParentFile()+"\\back"+System.currentTimeMillis()+".properties");
     		if(!backupFile.exists()) {
     			try {
					backupFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		}
           //  File backupFile = new File(file.getAbsoluteFile()+"s"+System.currentTimeMillis());  //准备备份文件名
             
             System.out.println("+++++++++++++++++++++++++++++++++++++++");
             System.out.println(backupFile.getName());
             System.out.println("+++++++++++++++++++++++++++++++++++++++");
             
             //File backupFile = new File("E:/myworkpaces/eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/mystudy/WEB-INF/classes/a.properties");
             //以下为备份，异常恢复机制
             System.out.println(file.getName());
             String newName = backupFile.getName().replace(".properties", "");
             
             file.renameTo(backupFile);
             if (!FileUtils.renameTo(file,backupFile.getName())){   //重命名原s文件，（备份）
                 logger.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Backup the file failed!");
                 tmpFile.delete(); //删除临时文件
             } else {
            	 System.out.println("Backup the file '\" + backupFile.getAbsolutePath() + \"'.");
                 if (logger.isDebugEnabled()) {
                     logger.debug("Backup the file '" + backupFile.getAbsolutePath() + "'.");
                 }
                 String tihuanName = file.getName().replace(".properties", "");
                 System.out.println("tihuanName"+tihuanName);
                 if (!FileUtils.renameTo(tmpFile, file.getName())) {   //临时文件重命名失败 （加密文件替换原失败）
                     logger.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Rename the tmp file failed!");
                     System.out.println("临时文件重命名失败 （加密文件替换原失败）");
                     if (FileUtils.renameTo(backupFile, file.getName())) {   //恢复备份
                         if (logger.isInfoEnabled()) {
                             logger.info("Restore the backup, success.");
                         }
                     } else {
                         logger.error("Restore the backup, failed!");
                     }
                 } else {  //（加密文件替换原成功）
                	 
                	 System.out.println("文件替换成功");
                     if (logger.isDebugEnabled()) {
                         logger.debug("Rename the file '" + tmpFile.getAbsolutePath() + "' -> '" + file.getAbsoluteFile() + "'.");
                     }

                     boolean dBackup = backupFile.delete();//删除备份文件

                     if (logger.isDebugEnabled()) {
                         logger.debug("Delete the backup '" + backupFile.getAbsolutePath() + "'.(" + dBackup + ")");
                     }
                 }
             }
         }

         }

	/**
	 * 判断文件是否未空行
	 * 
	 * @param line
	 * @return
	 */
	private boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * @param str
	 * 字符串是否为空的，为空返回true
	 * @return
	 */
	private boolean isBlank(String str) {
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

	protected Resource[] locations;

	@Override
	public void setLocations(Resource[] locations) { // 由于location是父类私有，所以需要记录到本类的locations中
		super.setLocations(locations);
		this.locations = locations;
	}

	@Override
	public void setLocation(Resource location) { // 由于location是父类私有，所以需要记录到本类的locations中
		super.setLocation(location);
		this.locations = new Resource[] { location };
	}
}
