package com.todoapplication.todo.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class ToDo {
  @Id
  private String id;

  @NotBlank
  @Size(max = 50)
  @Indexed(unique = true)
  private String desc;

  private Boolean status = false;

  private Date createdAt = new Date();

  public ToDo() {
    super();
  }

  public ToDo(String desc) {
    this.desc = desc;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatuus(Boolean status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt() {
    this.createdAt = createdAt;
  }


  @Override
  public String toString() {
    return String.format("ToDo[id=%s, desc='%s', status ='%s']", id, desc, status);
  }


}
