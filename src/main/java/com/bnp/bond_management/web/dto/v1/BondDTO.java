package com.bnp.bond_management.web.dto.v1;

import com.bnp.bond_management.database.entity.BondHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BondDTO {

    @NotNull
    @JsonProperty("id")
    private Integer id;
    @JsonProperty(value = "term")
    private int term;
    @JsonProperty("coupon")
    private double coupon;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("status")
    private BondStatusDTO status;
    @JsonProperty("bond_history")
    private List<BondHistory> bondHistoryList;
}
