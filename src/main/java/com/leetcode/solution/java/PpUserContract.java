package com.leetcode.solution.java;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PpUserContract {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 币种
     */
    private Integer coinId;
    /**
     * 券商id
     */
    private Integer brokerId;
    /**
     * 合约id
     */
    private Long contractId;
    /**
     * 用户余额
     */
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 创建时间
     */
    private Date createTime;

}
