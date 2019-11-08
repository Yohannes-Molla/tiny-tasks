package com.coyoapp.tinytask;

public class ResourceConstants {
  //Everyday at 8:00 AM
  public static final String CRON_EVERYDAY_EXPRESSION = "0 0 8 * * *";

  //used only for testing
  public static final String CRON_EVERY_TWO_SECONDS_EXPRESSION = "2 * * * * *";

  public static final String EMAIL_SUBJECT = "Unfinished Tiny Tasks";

  public static final String TINY_TASKS_V1_UNSECURED = "/v1/tiny-tasks/";
  public static final String TINY_TASKS_V1_SECURED = "/v1/tiny-tasks/secured/";
}
