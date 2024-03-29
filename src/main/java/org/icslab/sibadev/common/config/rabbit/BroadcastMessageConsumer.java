package org.icslab.sibadev.common.config.rabbit;

import org.icslab.sibadev.common.config.rabbit.domain.DeivceEstablishMessage;
import org.icslab.sibadev.common.config.rabbit.domain.DeviceControlResultMessage;
import org.icslab.sibadev.common.config.rabbit.domain.EstablishMessage;
import org.icslab.sibadev.common.config.rabbit.domain.KeepAliveMessage;
import org.icslab.sibadev.devices.device.services.DeviceEstablishService;
import org.icslab.sibadev.devices.test.services.DeviceControlResultProcessService;
import org.icslab.sibadev.devices.vhub.services.HubEstablishService;
import org.icslab.sibadev.devices.vhub.services.VirtualHubKeepAliveService;
import org.icslab.sibadev.mappers.DeviceMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BroadcastMessageConsumer {

    @Autowired
    private VirtualHubKeepAliveService virtualHubKeepAliveService;

    @Autowired
    private HubEstablishService hubEstablishService;

    @Autowired
    private DeviceEstablishService deviceEstablishService;

    @Autowired
    private DeviceControlResultProcessService deviceControlResultProcessService;

    @Autowired
    private DeviceMapper deivceMapper;


    //keep-alive consumer
    @RabbitListener(queues = {RabbitMQConstants.KEEP_ALIVE_QUEUE})
    public void keepAlive(final KeepAliveMessage message) {

        //Entity chargeOrder = MapperUtil.writeStringAsObject(message, Entity.class);
        virtualHubKeepAliveService.keep(message.getId());
        //System.out.println(message.getId());
    }

    //hub establish consumer
    @RabbitListener(queues = {RabbitMQConstants.ESTABLISH_QUEUE})
    public void establishAck(final EstablishMessage message) {
        hubEstablishService.establish(message.getId(), message.getIp(), message.getPort());
    }

    //device establish consumer
    @RabbitListener(queues = {RabbitMQConstants.DEVICE_ESTABLISH_QUEUE})
    public void deviceEstablishAck(final DeivceEstablishMessage message) {
        System.out.println("device connect");
        System.out.println(message);

        //디바이스가 연결되면 연결 디바이스 정보 저장
        //예외 처리 해야함.
        //서버가 꺼졌다가 켜지면 연결 데이터 그대로 남아있을것.
        Integer devId = deivceMapper.findDevId(message.getDevKey());
        if(message.getMsgType()==1){
            if(devId !=null){
                deivceMapper.deleteConnectedDevice(message.getMac());
                deivceMapper.createConnectedDevice(devId, message.getMac());
            }
        }
        else if(message.getMac()!=null && !message.getMac().isEmpty()){
            deivceMapper.deleteConnectedDevice(message.getMac());
        }

        deviceEstablishService.establish(message.getDevKey(), message.getMac(), message.getMsgType(), devId);
    }

    //device control result consumer
    @RabbitListener(queues = {RabbitMQConstants.DEVICE_CONTROL_QUEUE})
    public void controlResultReceive(final DeviceControlResultMessage message) {
        //System.out.println(message);
        deviceControlResultProcessService.process(message);
    }
}