package com.study.controller.first;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.utils.UploadUtils;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/byCommons")
	public void testUpload(HttpServletRequest request,  Model model) throws Exception {
		boolean flag = UploadUtils.uploadFile(request, null);
		System.out.println("结果是："+flag);
	}
}
