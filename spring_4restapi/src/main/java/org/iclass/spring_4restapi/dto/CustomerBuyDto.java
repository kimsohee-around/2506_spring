package org.iclass.spring_4restapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerBuyDto {
  private final String pcode;
  private final String pname;
  private final Integer price;
  private final Long money;

  @Override
  public String toString() {
    return "\n" + pcode + "\t" + pname + "\t" + price + "\t" + money + "\n";
  }

}
