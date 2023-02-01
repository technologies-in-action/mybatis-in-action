package com.github.yunfeng.demo;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseSqlTest {
    private static final String H2_URL = "jdbc:h2:mem:test";
    public static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = DataSourceBuilder.create().driverClassName("org.h2.Driver").url(H2_URL).username("").password("").build();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("test", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        BaseMappers.getMappers().forEach(configuration::addMapper);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public void executeSqlInFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:sql/" + filePath);
            String sql = Files.readString(file.toPath());
            Connection connection = DriverManager.getConnection(H2_URL, "", "");
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
