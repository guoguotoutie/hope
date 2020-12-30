package com.hopeshop;

import com.hopeshop.client.CategoryClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HsSearchService.class)
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategory(){
        List<String> names = this.categoryClient.queryNameByIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(names);

    }
}
