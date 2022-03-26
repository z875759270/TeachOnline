package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.utils.SensitiveWordUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

/**
 * @ClassName SenciController
 * @Author Zhanc
 * @Version 1.0
 * @Date 25/3/2022 下午8:39
 * @Description TODO
 **/
@RestController
@RequestMapping("back/senci")
public class SenciController {

    @PostMapping("edit")
    public ResponseEntity<Boolean> edit(@ApiParam("修改后的敏感词列表") String senciList) throws IOException {
        FileWriter fw = null;
        boolean isSuccess = false;
        try {
            File file = new File(SensitiveWordUtil.class.getResource("/").getPath() + "com/zhanc/teachonline/utils/sensi_word.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, false);
            fw.write(senciList);
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
        return ResponseEntity.ok(isSuccess);
    }


    @RequestMapping("manage")
    public ModelAndView toSenciManage(Model model) throws IOException {
        StringBuilder resSenci = new StringBuilder();
        File file = new File(SensitiveWordUtil.class.getResource("/").getPath() + "com/zhanc/teachonline/utils/sensi_word.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            if (!"".equals(line))
                resSenci.append(line).append("\r\n");
        }
        model.addAttribute("senciList", resSenci);
        return new ModelAndView("/back/senci-manage");
    }
}
