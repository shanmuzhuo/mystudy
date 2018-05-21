package com.study.controller.first;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.study.service.fitst.IInstantMessage;

/**
 * @author zzx
 * 即时消息，实现方法有ajax轮询，长轮询，websocket，redis。
 * 时间：2017年8月13日
 *
 */

/**
 * @author hasee
 *
 */
@Controller
@RequestMapping("/instant")
public class InstantMessage {

	private Gson json = new Gson();
	
	@Resource
	private IInstantMessage imservice;
	
	/**
	 * 普通轮询
	 * @param request
	 * @param model
	 * @return 查询表中的数据
	 */
	@RequestMapping("/ajax")
	@ResponseBody 
	public String doAjax(HttpServletRequest request, Model model) {
		String count = this.imservice.selectcountIndex();
		return count;
	}
	
	/**
	 * 长轮询
	 * 用一个死循环来维持链接，这样当数据没有变化时保持连接。，返回的结果顺序得不到保证
	 * @param request
	 * @param model
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping("/longAjax")
	@ResponseBody 
	public String longPolling(HttpServletRequest request, Model model) throws InterruptedException {
		String cont = this.imservice.selectcountIndex();
		while(true) {
			String a  = this.imservice.selectcountIndex();
			if(!a.equals(cont)) {
				return a; //数据发生变化，即返回数据，结束一个请求
			}else { //没有数据变化，将休眠 hold住连接
				Thread.sleep(5000);
			}
		}
	}
	
	
	
	/**
	 * 查询表中的数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("message")
	public String enterMessage(HttpServletRequest request, Model model) {
		String count = this.imservice.selectcountIndex();
		model.addAttribute("num", count);
		return "instantMessage";
	}
	
}
