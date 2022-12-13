package com.miniproject.krs.model;

import com.miniproject.krs.entity.DosenEntity;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class DosenModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String nip;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String jk;

    @NotBlank
    @NotEmpty
    private String alamat;

    @NotBlank
    @NotEmpty
    private String gelar;

    //constructor
    public DosenModel() {

    }

    public DosenModel(String nip, String name, String jk, String alamat, String gelar){
        this.nip = nip;
        this.name = name;
        this.jk = jk;
        this.alamat = alamat;
        this.gelar = gelar;
    }
    
    public DosenModel(DosenEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

}
