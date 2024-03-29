package org.icslab.sibadev.devices.device.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeviceDTO {

    @JsonIgnore
    private Long userId;

    private String authKey;

    @JsonProperty(value = "vHubId")
    private Integer vHubId;

    private Integer devId;

    private String devName;

    private String devDefName;

    private String devType;

    private String category;

    private Integer boxIdCnt;

    private Integer codeCnt;

    private Integer evCodeCnt;

    private Boolean haveEntry;

    private Timestamp modDate;

    private Timestamp depDate;
}
