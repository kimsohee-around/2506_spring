package org.iclass.spring_4restapi.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BuyDto {
  private final Integer buy_seq;
  private final String customer_id;
  private final String pcode;
  private final Integer quantity;
  private final Timestamp buy_date;

  @Override
  public String toString() {
    return "\n" + buy_seq + " \t " + customer_id + "\t" + pcode + "\t" + quantity
        + "\t" + buy_date + "\n";
  }

}
