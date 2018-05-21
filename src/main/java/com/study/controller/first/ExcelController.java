package com.study.controller.first;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.utils.first.ExcelUtil;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping("export")
    @ResponseBody
    public void exportFeedBack(HttpServletRequest request , Model model,HttpServletResponse response){
        String fileName = "反馈明细"+System.currentTimeMillis()+".xls"; //文件名 
        String sheetName = "反馈明细";//sheet名
        
        String [] title = new String[]{"Id","导航图标","反馈类型","内容","联系方式","应用Id","应用版本","反馈时间"};//标题
        
        String [][] values = new String[3][8];//标题
        
        for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				values[i][j] = i + j + "";
			}
		}
        
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
        
        //将文件存到指定位置  
        try {  
             this.setResponseHeader(response, fileName);  
             OutputStream os = response.getOutputStream();  
             wb.write(os);  
             os.flush();  
             os.close();  
        } catch (Exception e) {  
             e.printStackTrace();  
        }  
    }
    
     public void setResponseHeader(HttpServletResponse response, String fileName) {  
         try {  
              try {  
                   fileName = new String(fileName.getBytes(),"ISO8859-1");  
              } catch (UnsupportedEncodingException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
              }  
              response.setContentType("application/octet-stream;charset=utf-8");  
              response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
              response.addHeader("Pargam", "no-cache");  
              response.addHeader("Cache-Control", "no-cache");  
         } catch (Exception ex) {  
              ex.printStackTrace();  
         }  
    } 
}
