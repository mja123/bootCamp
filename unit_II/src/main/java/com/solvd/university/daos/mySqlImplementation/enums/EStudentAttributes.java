package com.solvd.university.daos.mySqlImplementation.enums;

public enum EStudentAttributes {
  NAME("name"),
  //AVERAGE("average"),
  EMAIL("email"),
  AGE("age"),
  YEARS_IN_DEGREE("year_in_degree"),
  ID("id"),
  ;

  private final String ATTRIBUTE;

  EStudentAttributes(String ATTRIBUTE) {
    this.ATTRIBUTE = ATTRIBUTE;
  }

  public String getATTRIBUTE() {
    return ATTRIBUTE;
  }
}
