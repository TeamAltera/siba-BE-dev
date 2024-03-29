package org.icslab.sibadev.devices.vhub.services;

import org.icslab.sibadev.devices.device.domain.DeviceShortDTO;
import org.icslab.sibadev.devices.vhub.domain.VirtualHubDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceGroupingService {

    public List<VirtualHubDTO> grouping(List<DeviceShortDTO> deviceList){
        List<VirtualHubDTO> hubInfo = new ArrayList<>();

        int vhubId = -1;
        List<DeviceShortDTO> devices = null;
        for(DeviceShortDTO item: deviceList){
            int curHubId = item.getVHubId();

            System.out.println(item);

            if(vhubId != curHubId){ //새로운 허브라면
                vhubId = curHubId;
                devices = new ArrayList<>();
                if(item.getAuthKey() !=null) devices.add(item);
                VirtualHubDTO  virtualHub = VirtualHubDTO.builder()
                        .vhubId(curHubId)
                        .hubKey(item.getHubKey())
                        .hubName(item.getHubName())
                        .hubStatus(item.getHubStatus())
                        .hubIp(item.getHubIp())
                        .hubPort(item.getHubPort())
                        .hubType(item.getHubType())
                        .devices(devices)
                        .build();
                hubInfo.add(virtualHub);
                continue;
            }

            devices.add(item);
        }

        return hubInfo;
    }
}
