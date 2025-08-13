package org.iclass.spring_2di.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CustomerApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(CustomerApplication.class, args);

    log.info("-----------------------");
    CustomerDao dao = context.getBean(CustomerDao.class);
    dao.setGroups();
    CustomerService service = context.getBean(CustomerService.class);
    service.test();
    CustomerController controller = context.getBean(CustomerController.class);
    controller.test();
  }
}
