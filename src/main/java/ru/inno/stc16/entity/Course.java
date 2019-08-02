package ru.inno.stc16.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class Course {

  private static int LAST_ID = 0;

  private int id;
  private String title;

  public Course() {
    id = LAST_ID++;
  }

  public Course(String title) {
    this();
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override public String toString() {
    return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("title='" + title + "'")
        .toString();
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return id == course.id &&
        Objects.equals(title, course.title);
  }

  @Override public int hashCode() {
    return Objects.hash(id, title);
  }
}
