---
title: study
data: 2017年8月24日16:20:31
description: 这是一个自我学习的项目，会不断添加小功能，争取实用
---

# 个人学习文档

## 对应的文件夹内容

### excel操作

- com.stu.demo_1------------------------excel导出
- com.stu.demo_1.controller-----------contro层
- com.stu.demo_1.utils------------------excel工具类

### 即时消息，webscoket，demo2
- 通过ajax轮询来完成  [实时消息的实现方式](https://shanmuzhuo.github.io/myblog.github.io/2017/08/22/%E5%AE%9E%E6%97%B6%E6%B6%88%E6%81%AF/)
- 相关文件：instantMessage.jsp 轮询界面
- WebSocket.jsp。界面

### 翻译util，用百度翻译
- com.baidu.translate： ___百度翻译相关类
	- Languages.java ,目标语言，源语言不确定可以用auto，目标语言必须确定

### com.common.utils
- TranslateUtil.java：___ 主要使用该类进行语言翻译，返回结果为一条json字符串，返回结果暂未处理
- EncodeConvertUtil.java:___ 进行字符编码转换工具类
- MD5Util.java:___MD5工具类
- DecryptPropertyPlaceholderConfigurer：___对配置文件的参数做处理
- FileUtil:___文件处理工具类