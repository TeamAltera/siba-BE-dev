package org.icslab.sibadev.devices.device.domain;

import lombok.Data;

@Data
public class BtnDerivationJoinVO {

    private int boxId;

    private int btnCode;

    private int eventCode;

    private Boolean isSpread;

    private String btnName;

    private String type;

    private int idx;

    private Integer cBoxId;

    private double mx;

    private double my;

    private double zx;

    private double zy;
}
