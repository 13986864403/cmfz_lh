package com.baizhi.service;

import com.baizhi.dao.AudioDAO;
import com.baizhi.entity.Audio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;
import java.util.UUID;

@Service
@Transactional
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDAO audioDAO;
    //添加章节
    @Override
    public Audio addAudio(Audio audio) {
        audio.setId(UUID.randomUUID().toString());
        audioDAO.inseret(audio);

        return audio;
    }
}
