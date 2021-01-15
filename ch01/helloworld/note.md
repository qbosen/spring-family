## ch0101_helloworld

这是一个简单的web应用，springboot会通过内建的tomcat容器启动项目。
* 重点是 如何测试这样的web工程

1. `webEnvironment=RANDOM_PORT` 启动一个随意端口的Tomcat
   `@LocalServerPort` 自动注入随机端口
   `@TestRestTemplate` Spring boot 提供一个TestRestTemplate，作为 Http Client
   存在启动Tomcat的开销

2. `@AutoConfigureMockMvc` 启动自动配置 MockMvc
   注入mockmvc 可执行 http client 的功能
   通过 `MockHttpServletRequestBuilder` 构建 `request`
   通过 `StatusResultMatchers` 构建 `matcher` 校验结果
   通过 `MockMvcResultHandlers` 构建 `handler` 输出结果
   console 没有打印 Tomcat日志信息，Tomcat 不启动
   存在启动整个 spring 的开销

