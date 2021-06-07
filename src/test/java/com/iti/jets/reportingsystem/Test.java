package com.iti.jets.reportingsystem;

import com.iti.jets.reportingsystem.entities.Concession;
import com.iti.jets.reportingsystem.models.ConcessionModel;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test {
    private ModelMapper modelMapper = new ModelMapper();

    @org.junit.jupiter.api.Test
    public void Test1() {
        ConcessionModel dto = new ConcessionModel(1, "meleiha");
        Concession entity = modelMapper.map(dto, Concession.class);
        assertEquals(entity.getConcessionName(), dto.getConcessionName());
    }

    @org.junit.jupiter.api.Test
    public void Test() {
        Concession entity = new Concession();
        entity.setId(1);
        entity.setConcessionName("melieha");
        ConcessionModel dto = modelMapper.map(entity, ConcessionModel.class);
        assertEquals(entity.getConcessionName(), dto.getConcessionName());
    }
}
