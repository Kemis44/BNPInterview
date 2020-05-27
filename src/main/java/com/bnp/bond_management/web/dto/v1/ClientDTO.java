package com.bnp.bond_management.web.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("surname")
    private String surname;

    @NotBlank
    @Size(min = 10, max = 10)
    @JsonProperty("bornNumber")
    private String bornNumber;

    @JsonProperty("bonds")
    private List<BondDTO> bonds;
}
