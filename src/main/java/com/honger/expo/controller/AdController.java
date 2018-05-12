package com.honger.expo.controller;

import com.honger.expo.dto.response.aboutUs.AboutUsResponse;
import com.honger.expo.dto.response.status.ResponseJSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by chenjian on 2018/5/9.
 */
@Controller
@RequestMapping(value = "/ad")
public class AdController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAdHomepage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,
                                      @RequestParam("src") String src, @RequestParam("uid") String uid) throws InterruptedException {
        Cookie src1 = new Cookie("src", src);
        Cookie uid1 = new Cookie("uid", uid);
        src1.setMaxAge(30 * 60);
        uid1.setMaxAge(30 * 60);
        src1.setPath("/");
        uid1.setPath("/");
        httpServletResponse.addCookie(src1);
        httpServletResponse.addCookie(uid1);
        Thread.sleep(3000);
        return "redirect:/";
    }
}
