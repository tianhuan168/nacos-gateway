# job-service 

> 本项目支撑cvs/cms中的job定时任务,通过可视化界面操作中心（xxl-job-admin）来建立相关job任务,并收集job相关执行的日志信息。</br>
> 后续通过集成邮件/钉钉来实现异常监控报警


### 技术选型

| 框架          |    作用描述 |
| :------:     |    :-----:  |
| springboot   | 基础框架|
| spring-cloud | 云服务|
| rocketMQ     | 消息监听|
| xxl-job      | 定时任务监控|
| spring-redis | 缓存 |
| aliyun-ons| 开放搜索|
| spring-boot-starter-freemarker| 邮件模板|
| nacos| 注册中心 |


#### 迁移流程

- 引入xxl-job-core  
```
# 当前引用版本号为2.1.2 支持@xxlJob注解，替换早起的jobHandler
<!-- xxl-job-core -->
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
    <version>2.1.2</version>
</dependency>

```

- 清理spring-quarzter的相关配置
    + config
    + schdule
    + job-listener
    
- 按照job的group(组)概念，把相同组的job任务整合成一个class，内部通过@xxlJob区分不同的job任务
    
<table>
  	<tr>
		<td>分组</td>
		<td>描述</td>
		<td>合计job任务数</td>
	<tr>
	<tr>
		<td>ActivityMessageJob</td>
		<td>活动相关job</td>
		<td>5</td>
	</tr>
  <tr>
    <td>AnalyzeJob</td>
    <td>市场表现</td>
    <td>9</td>
  </tr>
   <tr>
     <td>BrandJob</td>
     <td>牌相关job </td>
     <td>4</td>
   </tr>
 <tr>
   <td>CmsCurrentRateJob</td>
   <td>统计最新 汇率 job  </td>
   <td>1</td>
 </tr>
 <tr>
   <td>CvsJob</td>
   <td>cvs分组部分job任务 </td>
   <td>10</td>
 </tr>
 <tr>
   <td>IndustryMapJob</td>
   <td>产业链相关job </td>
   <td>5</td>
 </tr>
 <tr>
   <td>InitJob</td>
   <td>初始化 job（后续本组job会完全停止，job任务从message中触发） </td>
   <td>21</td>
 </tr>
 <tr>
   <td>InsightJob</td>
   <td>行业洞察 job任务</td>
   <td>16</td>
 </tr>
  <tr>
    <td>NewsJob</td>
    <td>资讯推送相关job</td>
    <td>4</td>
  </tr>
   <tr>
     <td>PushJob</td>
     <td>所有推送相关job</td>
     <td>13</td>
   </tr>
  <tr>
    <td>TryOutJob</td>
    <td>校园活动试用账号到期提醒</td>
    <td>1</td>
  </tr>
</table> 

- xxl-job资源文件配置。采用nacos中统一管理
  
  ```java
  # 引入nacos的配置文件
  spring.cloud.nacos.config.ext-config[13].group=job
  spring.cloud.nacos.config.ext-config[13].data-id=xxl-job.properties
  spring.cloud.nacos.config.ext-config[13].refresh=true
  ```
  
- 控制中心配置相关的job任务以及cron，具体参考
[教程](https://www.xuxueli.com/xxl-job/ "教程")

- 其他待续

 



