package com.github.yunfeng.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
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
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class BaseSqlTest {
    private static final String H2_URL = "jdbc:h2:mem:test";
    private static final String DAO_SUFFIX = "Dao";
    private static final String DEFAULT_PACKAGE_NAME = "com.github.yunfeng.demo";
    private static final String BASE_PACKAGE = System.getenv("BASE_PACKAGE");
    private static final String basePackage = StringUtils.defaultIfBlank(BASE_PACKAGE, DEFAULT_PACKAGE_NAME);
    public static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = DataSourceBuilder.create().driverClassName("org.h2.Driver").url(H2_URL).username("")
                .password("").build();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("test", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        getMappers().forEach(mapper -> {
            configuration.addMapper(mapper);
            log.info("add mapper: {}", mapper);
        });
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

    private static Set<Class<?>> getMappers() {
        Set<String> classes = ClazzUtils.getClazzName(basePackage);
        return classes.stream().filter(clazz -> clazz.endsWith(DAO_SUFFIX)).map(BaseSqlTest::getClassByName)
                .filter(BaseSqlTest::classHasMapperAnnotation).collect(Collectors.toSet());
    }

    private static boolean classHasMapperAnnotation(Class<?> clazz) {
        List<Annotation> declaredAnnotations = Arrays.asList(clazz.getDeclaredAnnotations());
        return declaredAnnotations.stream().anyMatch(annotation -> annotation.annotationType().equals(Mapper.class));
    }

    private static Class<?> getClassByName(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
