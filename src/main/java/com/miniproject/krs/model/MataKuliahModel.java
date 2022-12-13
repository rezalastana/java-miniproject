package com.miniproject.krs.model;

import com.miniproject.krs.entity.MataKuliahEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MataKuliahModel {
    public String id;

    @NotBlank
    @NotEmpty
    public String code;

    @NotBlank
    @NotEmpty
    public String name;

    @NotBlank
    @NotEmpty
    public String sks;
    
    //constructor
    public MataKuliahModel(){

    }

    public MataKuliahModel(MataKuliahEntity entity){
        BeanUtils.copyProperties(entity, this);
    }
}
