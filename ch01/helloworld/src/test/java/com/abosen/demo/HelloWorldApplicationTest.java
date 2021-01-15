package com.abosen.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author qiubaisen
 * @date 2019-03-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc
public class HelloWorldApplicationTest {

    /*
     * webEnvironment=RANDOM_PORT 启动一个随意端口的Tomcat
     * @LocalServerPort 自动注入随机端口
     * @TestRestTemplate Spring boot 提供一个TestRestTemplate，作为 Http Client
     * 存在启动Tomcat的开销
     */
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void helloWorld() {
        String url = "http://localhost:" + port + "/hello";
        String response = testRestTemplate.getForObject(url, String.class);
        assertEquals("Hello World!", response);
        log.info("response: {}", response);
    }

    /*
     * @AutoConfigureMockMvc 启动自动配置 MockMvc
     * mockmvc可执行 http client 的功能
     * print 打印 mock http 详细信息
     * console 没有打印 Tomcat日志信息，Tomcat 不启动
     * full Spring application context is started
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloWorld2() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().is(200))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("Hello World!"))
                .andReturn();
    }

}