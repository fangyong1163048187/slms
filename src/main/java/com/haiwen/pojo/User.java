package com.haiwen.pojo;


public class User {

  private long id;
  private String username;
  private String password;
  private String photo;
  private long role;

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", photo='" + photo + '\'' +
            ", role=" + role +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public long getRole() {
    return role;
  }

  public void setRole(long role) {
    this.role = role;
  }
}
