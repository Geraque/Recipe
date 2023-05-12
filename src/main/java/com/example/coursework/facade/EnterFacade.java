package com.example.coursework.facade;

import com.example.coursework.dto.EnterDTO;
import com.example.coursework.entity.Enter;
import org.springframework.stereotype.Component;

@Component
public class EnterFacade {

    public EnterDTO enterToEnterDTO(Enter enter) {
        EnterDTO enterDTO = new EnterDTO();
        enterDTO.setEnterId(enter.getEnterId());
        enterDTO.setUserId(enter.getUserId());

        return enterDTO;
    }

}
