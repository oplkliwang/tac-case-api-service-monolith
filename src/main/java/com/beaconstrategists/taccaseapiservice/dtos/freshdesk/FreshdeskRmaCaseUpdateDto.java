package com.beaconstrategists.taccaseapiservice.dtos.freshdesk;

import com.beaconstrategists.taccaseapiservice.dtos.RmaCaseUpdateDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
//@JsonInclude(JsonInclude.Include.NON_NULL)  //fixme: check on this
public class FreshdeskRmaCaseUpdateDto extends RmaCaseUpdateDto {

    private String key;
    @JsonSetter
    public void setKey(String value) {
        this.key = value;
        markFieldPresent("key");
    }

    private String tacCase;
    @JsonSetter
    public void setTacCase(String value) {
        this.tacCase = value;
        markFieldPresent("tacCase");
    }

    private Long ticket;
    @JsonSetter
    public void setTicket(Long value) {
        this.ticket = value;
        markFieldPresent("ticket");
    }

}
