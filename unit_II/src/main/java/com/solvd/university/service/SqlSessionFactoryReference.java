package com.solvd.university.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionFactoryReference {
  private static final SqlSessionFactoryReference INSTANCE = new SqlSessionFactoryReference();
  private SqlSessionFactory sessionFactory;
  private static final Logger LOGGER = LogManager.getLogger(SqlSessionFactoryReference.class);

  private SqlSessionFactoryReference() {
    configSqlSessionFactory();
  }

  private void configSqlSessionFactory() {
    Reader reader = null;
    try {
      reader = Resources.getResourceAsReader("mybatis-config.xml");
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }
    sessionFactory = new SqlSessionFactoryBuilder().build(reader);
  }

  public SqlSessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static SqlSessionFactoryReference getINSTANCE() {
    return INSTANCE;
  }
}
