package org.icslab.sibadev.devices.device.domain.textboxgraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.icslab.sibadev.devices.test.domain.TestLogVO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TextBoxGraphDTO {

    private String devAuthKey;

    private Integer devId;

    private String devName;

    private Integer vHubId;

    private Integer blockIdCounter;

    private Integer codeIdCounter;

    private Integer eventCodeIdCounter;

    private Boolean haveEntry;

    private List<TextBoxDTO> pallet;

    private List<LinkerDTO> linkers;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<TestLogVO> testLogList;
}
