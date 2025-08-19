package org.iclass.spring_3mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.spring_3mybatis.dto.BuyDto;
import org.iclass.spring_3mybatis.dto.CustomerBuyDto;

@Mapper
public interface BuyMapper {
  List<BuyDto> selectByCustomer(String customer_id);

  List<BuyDto> selectByPcode(String pcode);

  List<BuyDto> selectByYear(String year);

  int selectSumByPcode(String pcode);

  // join
  List<CustomerBuyDto> selectSaleByCustomer(String customer_id);

  // count 집계 함수
  Map<String, Integer> selectCountByYear(String year);

  List<Map<String, Object>> selectAllCountByYear();
}
