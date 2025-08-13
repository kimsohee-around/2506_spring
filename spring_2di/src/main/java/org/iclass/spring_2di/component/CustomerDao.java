package org.iclass.spring_2di.component;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerDao {
  private List<String> groups;

  public void setGroups() {
    this.groups = List.of("user", "admin", "manager");
    log.info("CustomerDao setter - groups : {} ", groups.toString());
  }

  

}
