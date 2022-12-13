package com.miniproject.krs.controller;


import com.miniproject.krs.model.FakultasModel;
import com.miniproject.krs.model.JurusanModel;
import com.miniproject.krs.service.FakultasService;
import com.miniproject.krs.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/jurusan")
public class JurusanController {
    private JurusanService jurusanService;
    private FakultasService fakultasService;
    
    @Autowired
    public JurusanController(JurusanService jurusanService, FakultasService fakultasService) {
        this.jurusanService = jurusanService;
        this.fakultasService = fakultasService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("jurusan/index.html");
        List<JurusanModel> result = jurusanService.getAll();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("jurusan/add.html");
        // List<FakultasModel> result = fakultasService.getAll(); kalau engga ingin langsung deklarasi manual
        view.addObject("jurusan", new JurusanModel());//"jurusan" penamaan untuk mengambil data Jurusan
        view.addObject("fakultasList",fakultasService.getAll()); //"fakultasList" penamaan untuk mengambil semua data Fakultas
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("jurusan") JurusanModel request, BindingResult result){
        ModelAndView view = new ModelAndView("jurusan/add.html");
        if (Boolean.FALSE.equals(jurusanService.validCode(request))){
            FieldError fieldError = new FieldError("jurusan","code","Code "+ request.getCode() +" already exist");
            result.addError(fieldError);
        } 

        if (Boolean.FALSE.equals(jurusanService.validName(request))) {
            ObjectError objectError = new FieldError("jurusan","code","Code "+ request.getName() +" already exist");
            result.addError(objectError);
        }

        if(result.hasErrors()){
            view.addObject("jurusan", request);
            return view;
        }

        this.jurusanService.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        JurusanModel jurusan = this.jurusanService.getById(id);
        if (jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        List<FakultasModel> fakultas = fakultasService.getAll();//abil data fakultas

        ModelAndView view = new ModelAndView("jurusan/edit.html");
        view.addObject("jurusan", jurusan);
        view.addObject("fakultasList", fakultas);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("jurusan") JurusanModel request, BindingResult result){
        if (result.hasErrors()) {
            ModelAndView view = new ModelAndView("jurusan/edit.html");
            view.addObject("jurusan", request);
            return view;
        }
        this.jurusanService.update(request.getId(), request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        JurusanModel jurusan = jurusanService.getById(id);
        if (jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("jurusan/detail.html");
        view.addObject("data", jurusan);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute JurusanModel request){
        JurusanModel jurusan = jurusanService.getById(request.getId());
        if (jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        this.jurusanService.delete(request.getId());
        return new ModelAndView("redirect:/jurusan");
    }


}
