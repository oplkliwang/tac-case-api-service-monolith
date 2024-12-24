package com.beaconstrategists.taccaseapiservice.dtos.freshdesk;

import com.beaconstrategists.taccaseapiservice.dtos.TacCaseUpdateDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)  //fixme: check on this
public class FreshdeskTacCaseUpdateDto extends TacCaseUpdateDto {

    private String key;
    @JsonSetter
    public void setKey(String value) {
        this.key = value;
        markFieldPresent("key");
    }

    private Long ticket;
    @JsonSetter
    public void setTicket(Long value) {
        this.ticket = value;
        markFieldPresent("ticket");
    }
}
