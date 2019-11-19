package com.issac.spring.ssm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * @author: ywy
 * @date: 2019-10-31
 * @desc: @WebAppConfiguration 在单元测试中不用启动Servlet容器获取web应用上下文
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
@WebAppConfiguration
public class ItemControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() {
        //初始化mock对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void findItem() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/showEdit"
        ).param("id", "1"))
                .andExpect(MockMvcResultMatchers.view().name("item-edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse());
    }

    @Test
    public void findItem1() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/findItem3"
        ).param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse());
    }

    @Test
    public void findItem2() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(
                "/findItem4/1"
        ))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse());
    }
}