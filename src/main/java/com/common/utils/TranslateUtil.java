package com.common.utils;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.translate.Languages;
import com.baidu.translate.TransApi;

/**
 * @author zzx 翻译文本。 生成的APP_ID以及密钥 翻译帮助类
 */
public class TranslateUtil {

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = AESUtil.AESDecode("wqsRVO/1WDviOHoUzTevKDHM1GhxlMxa5547oXsAdI4=");
	private static final String SECURITY_KEY = AESUtil.AESDecode("B8JZsnHM7uKBUYtf3jQfA36WeIzsiw4/kMoz7aAPaps=");

	/**
	 * @param to
	 * 			目标语言
	 * @param args
	 * 			要翻译的字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unused")
	public static String doTrans(String to, String... args) throws UnsupportedEncodingException {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		StringBuffer sb = new StringBuffer();
		if (null != args && !"".equals(args)) {
			for (String string : args) {
				sb.append(string + "\n");// 翻译多个文本或者字段的时候用换行符隔开
			}
		}
		String query = sb.toString();

		// System.out.println(api.getTransResult(query, "auto", to));
		return api.getTransResult(query, "auto", to);
	}

	public static void main(String[] args) {
		try {
			String str = TranslateUtil.doTrans(Languages.wyw, "条条大路通罗马", "世上的一切都是美好的","周游世界，劈柴喂马");
			JSONObject jsonObject = JSONObject.parseObject(str);
			// System.out.println(jsonObject.getString("trans_result"));

			JSONArray arrjson = JSONArray.parseArray(jsonObject.getString("trans_result"));
			for (int i = 0; i < arrjson.size(); i++) {
				System.out.println(((JSONObject) (arrjson.get(i))).getString("src") + "----翻译结果-----"
						+ ((JSONObject) (arrjson.get(i))).getString("dst"));
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
