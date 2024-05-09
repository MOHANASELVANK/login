package com.example.sample.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.domain.form;
import com.example.sample.repository.formrepo;

@Service
public class formservice {

    @Autowired
    private final formrepo exformrepo;

    public formservice(formrepo exformrepo) {
        this.exformrepo = exformrepo;
    }

    public form saveform(String username, String password) {
        form f = new form();
        f.username = username;
        f.password = password;
        return exformrepo.save(f);
    }

    public ResultSet retform() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college", "root", "");
        PreparedStatement preparedStatement = conn.prepareStatement("select * from login");

        ResultSet rs = preparedStatement.executeQuery();
        return rs;

    }

}
