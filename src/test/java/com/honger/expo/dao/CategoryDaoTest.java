package com.honger.expo.dao;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.service.CategoryService;
import lombok.Synchronized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CategoryDaoTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testGetHomePageCategory() throws InterruptedException {
        List<CategoryListResponse> homePageCategory = categoryService.getHomePageCategory();
        System.out.println();
    }

    @Test
    public void testGetAllCategory(){
        List<Category> allCategory = categoryMapper.getAllCategory();
        System.out.println(allCategory);
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        String a = "abcda";
        String b = "aabcb";
        boolean s = testEqual(a,b);
        System.out.println(s);

        FutureTask futureTask = new FutureTask<Integer>(new Test1());
        futureTask.run();
        Integer o = (Integer) futureTask.get();
        System.out.println(o);
    }

    private boolean testEqual(String a,String b) {
        if(a.length() != b.length())
            return false;


        HashMap<Character,Integer> map1 = new HashMap<>();
        HashMap<Character,Integer> map2 = new HashMap<>();
        int length = a.length();
        for(int i = 0;i < length;i++){
            char c = a.charAt(i);
            if(!map1.containsKey(c))
                map1.put(c,0);
            else{
                Integer integer = map1.get(c);
                map1.put(c,integer+1);
            }
        }

        for(int i = 0;i < length;i++){
            char c = b.charAt(i);
            if(!map2.containsKey(c))
                map2.put(c,0);
            else{
                Integer integer = map2.get(c);
                map2.put(c,integer+1);
            }
        }

        for(Map.Entry<Character, Integer> i : map1.entrySet()) {
            Character key = i.getKey();
            Integer value = i.getValue();
            boolean flag = false;
            for (Map.Entry<Character, Integer> j : map2.entrySet()) {
                if (j.getKey().equals(key) && value.equals(j.getValue())) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                return false;

        }
        return true;
    }

    class Test1 implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 100;
        }
    }
}

