package org.icslab.sibadev.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.icslab.sibadev.devices.vhub.domain.VirtualHubHostVO;
import org.icslab.sibadev.devices.vhub.domain.VirtualHubVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface VirtualHubMapper {

    void updateDeviceLinkHub(@Param("vhubId") Integer vhubId, @Param("devId") Integer devId);

    void createHub(Map<String, Object> map);

    VirtualHubVO getHubOwner(String hubKey);

    void establishHub(
            @Param("hubKey") String hubKey,
            @Param("hubIp") String hubIp,
            @Param("hubPort") Integer hubPort,
            @Param("status") Boolean status
    );

    void updateHubStatus(@Param("hubKey") String hubKey, @Param("status") Boolean status);

    void updateDeviceUnlink(Integer devId);

    void unlinkAllHub();

    VirtualHubHostVO getVirtualHubHostInfo(Integer vhubId);

    VirtualHubHostVO getVirtualHubHostInfoWithKey(String hubKey);

    VirtualHubHostVO getVirtualHubHostInfoByDevId(Integer devId);

    List<Integer> getAllLinkedRepoId(Integer hubId);
}
