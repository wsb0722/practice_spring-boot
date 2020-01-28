package com.example.simple_board.service.freeboard;

import com.example.simple_board.model.Freeboard;
import com.example.simple_board.repository.FreeboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class FreeboardInfoService {
    @Autowired private FreeboardRepository freeboardRepository;
    @Autowired private HttpSession session;

    public String getFreeboardPost(String stringFreeId){
        Long freeid = Long.parseLong(stringFreeId);
        Freeboard freeboard = freeboardRepository.findByFreeId(freeid);

        if (freeboard == null) return "freeboard";

        session.setAttribute("freeboard", freeboard);
        return "freeBoardInfo";
    }

    public void update(String stringFreeId, String title, String content) {
        Long freeId = Long.parseLong(stringFreeId);
        Freeboard freeboard = freeboardRepository.findByFreeId(freeId);

        if (freeboard == null) return;

        freeboard.setTitle(title);
        freeboard.setContent(content);
        LocalDateTime dateTime = LocalDateTime.now();
        freeboard.setModifiedDate(dateTime);
        freeboardRepository.save(freeboard);
    }
}