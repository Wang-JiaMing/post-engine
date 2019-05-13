# 接口引擎
> author:wangjiaming
### 前言
目前该接口支持restful的快速对接工作，由于日常面对很多restful接口，
而每次实现该接口都需要编写大量的实现类，故开发这个接口引擎。用户可以通过简单配置application.properties和flowcore.properties实现快速接口对接工作。
### 使用方式

需要配置的文件

- application.properties
> 主要配置flowcore.perperties绝对路径和数据库访问方式等
- flowcore.perperties 
> 接口引擎核心配置文件

**1.application.properties**
- flowcore.path 需要配置成flowcore.preperties的绝对路径
>例如:flowcore.path=/Users/wangjiaming/workspace/java/post-engine/src/main/resources/flowcore.properties
- spring.datasource.druid.url   需要替换成访问数据库的ip端口以及用例，目前暂时只支持oracle数据库
- spring.datasource.druid.username  数据库账号
- spring.datasource.druid.password  数据库密码

**flowcore.properties**
- flow.port.jobtime 
> 队列定时运行配置，使用cron配置方式<br>
> flow.port.jobtime=0/30 * * * * ?

- flow.post.queue
> 引擎队列，配置方式：flow.post.queue=1;2;3;4;5    <注意使用英文半角的符号> ，**该队列会以从左到右的顺序遍历一下配置**
- flow.post.sql.队列序号
> sql返回json参数，配置该队列在指定数据库下执行的sql<br>，之后通过接口返回的接口转换成json传到指定接口中
> flow.post.sql.1=select 'test' as loginName,'test' as menuCode from dual
- flow.post.constant.队列序号
> 常量参数，配置该队列的常量，常用在通过账号密码获取token之类的接口中<br>
> flow.post.constant.1={"loginName":"test","menuCode":"test"}
- flow.post.url.队列序号
> 队列接口，获取sql返回的json或者常量参数传到配置接口中<br>
> flow.post.url.1=http://localhost:9110/menus/queryAllLoginMenuByLoginName
- flow.post.replace.队列序号
> 参数名替换配置，由于sql返回的key是全大写，所以可以通过这个配置，对全大写的key替换到想要的方式<br>
> flow.post.replace.1=LOGINNAME-loginName;MENUCODE-menuCode

