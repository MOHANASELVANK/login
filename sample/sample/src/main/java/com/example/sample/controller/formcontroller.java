package com.example.sample.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.sample.domain.form;
import com.example.sample.service.formservice;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class formcontroller {

    @Autowired
    private formservice service;

    @GetMapping("/")
    public String home() {
        return "fillform";
    }

    // @GetMapping("/getin")
    // public String disp() {
    // return "submitted";
    // }

    @PostMapping("/getin")
    public String savehere(@ModelAttribute("leo") form leo) {

        form oauthUser = service.saveform(leo.getUsername(), leo.getPassword());

        System.out.print(oauthUser);
        if (Objects.nonNull(oauthUser)) {
            return "redirect:/comp";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/comp")
    public String comp(Model model) throws SQLException {
        ResultSet rs = service.retform();
        List<Map<String, String>> forms = new ArrayList<>();

        while (rs.next()) {
            Map<String, String> val = new HashMap<>();
            val.put("Username", rs.getString(1));
            val.put("Password", rs.getString(2));
            forms.add(val);
        }

        model.addAttribute("forms", forms);
        return "submitted";
    }
}
