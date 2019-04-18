package com.example.adminmanage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.adminmanage.entity.User;
import com.example.adminmanage.entity.UserType;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = true)
public class AdminManageApplicationTests {

    private MockMvc mockMvc;
    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @Autowired
    private WebApplicationContext context;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        user1 = new User();
        user1.setUsername("admin1");
        user1.setAccountStatus(true);
        user1.setUserType(UserType.Admin);
        user1.setRemarksInfo("hello");

        user2 = new User();
        user2.setUsername("client1");
        user2.setAccountStatus(true);
        user2.setUserType(UserType.DataAnalyser);
        user2.setRemarksInfo("hey");

        user3 = new User();
        user3.setUsername("client1");
        user3.setAccountStatus(true);
        user3.setUserType(UserType.DataManager);
        user3.setRemarksInfo("haha");

        user4 = new User();
        user4.setUsername("admin1");
        user4.setAccountStatus(false);
        user4.setUserType(UserType.Admin);
        user4.setRemarksInfo("hello");

        mockMvc.perform(
                put("/admin/manage")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONBytes(user1))
        );

        mockMvc.perform(
                put("/admin/manage")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONBytes(user2))
        );
    }

    @Test
    public void register() throws Exception {



        String response = mockMvc.perform(
                put("/admin/manage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .param("userName", "James")
//                        .param("userType", UserType.ADMIN)
//                        .param("accountStatus", "true")
//                        .param("remarksInfo", "hello")
                        .content(JSON.toJSONString(user3))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
//        System.out.println(response);
    }

    @Test
    public void putAndModify() throws Exception {



        String response = mockMvc.perform(
                post("/admin/manage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONBytes(user4))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
//        System.out.println(response);
    }

    @Test
    public void getAll() throws Exception {
        String response = mockMvc.perform(
                get("/admin/manage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNum", "1")
                        .param("pageSize", "3")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
//        System.out.println(response);
    }

    @Test
    public void login() throws Exception {
        User u = new User();
        u.setUsername("admin1");
        u.setPassword("123456");

        String response = mockMvc.perform(
                put("/admin/login")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONBytes(u))
//                        .param("userName", "admin1")
//                        .param("passWord", "123456")

        )
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
//        System.out.println(response);
    }

}
