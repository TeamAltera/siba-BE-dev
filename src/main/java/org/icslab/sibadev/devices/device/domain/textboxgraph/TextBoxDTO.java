package org.icslab.sibadev.devices.device.domain.textboxgraph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.icslab.sibadev.devices.device.domain.StateRuleDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TextBoxDTO {
    private PositionDTO pos;

    private int type;

    private String preorder;

    private String postorder;

    private List<ParentBoxDTO> parentBox;

    private Integer headRow;

    private Integer footRow;

    private int id;

    private boolean linking;

    private boolean linked;

    private InfoDTO info;

    private List<StateRuleDTO> rules;
}

/*@Data
@Builder
class Position{
    private double top;

    private double left;
}

@Data
class ParentBox{
    private int parentId;

    private int code;
}*/