package net.ejercicio.springboot.controller;

import net.ejercicio.springboot.dto.ConnectionRecordDto;
import net.ejercicio.springboot.entity.ConnectionRecord;
import net.ejercicio.springboot.service.ConnectionRecordService;
import net.ejercicio.springboot.service.GeneratedSequenceService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/connection_record")
public class ConnectionRecordController {

    @Autowired
    private ConnectionRecordService connectionRecordService;

    @Autowired
    private GeneratedSequenceService generatedSequenceService;

    @PostMapping("/insert")
    public ResponseEntity<ConnectionRecordDto> insert(@RequestBody ConnectionRecordDto connectionRecord){
        connectionRecord.setId(generatedSequenceService.getSequenceNumber(ConnectionRecord.SEQUENCE_NAME));
        ConnectionRecordDto savedConnectionRecord = connectionRecordService.createConnectionRecord(connectionRecord);
        return new ResponseEntity<>(savedConnectionRecord, HttpStatus.CREATED);
    }

    @PostMapping("/insertMany")
    public ResponseEntity<List<ConnectionRecordDto>> insertMany(@RequestBody List<ConnectionRecordDto> connectionRecords){
        List<ConnectionRecordDto> savedConnectionRecords = new ArrayList<>();

        for(ConnectionRecordDto connectionRecord : connectionRecords){
            connectionRecord.setId(generatedSequenceService.getSequenceNumber(ConnectionRecord.SEQUENCE_NAME));
            ConnectionRecordDto savedConnectionRecord = connectionRecordService.createConnectionRecord(connectionRecord);
            savedConnectionRecords.add(savedConnectionRecord);
        }

        return new ResponseEntity<>(savedConnectionRecords, HttpStatus.CREATED);
    }
}
