package ru.ancient.serviceconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ancient.serviceconsumer.DTO.CompanyDto;
import ru.ancient.serviceconsumer.DTO.UserDto;
import ru.ancient.serviceconsumer.enums.Sex;
import ru.ancient.serviceconsumer.feignClients.Untitled1Client;
import java.util.List;
import java.util.Locale;

@Controller
public class MyController {
    @Autowired
    Untitled1Client untitled1Client;
// Получение всех сотрудников
    @GetMapping("/allusers")
    public String index(Model model){
        List<UserDto> list = untitled1Client.getAllUsers();
        model.addAttribute("list", list);
        return "allusers";
    }
// Главная страница
    @GetMapping("/")
    public String mainPage(Model model){
        List<CompanyDto> list = untitled1Client.getAllCompany();
        model.addAttribute("list", list);
        return "index";
    }
// Получение сотрудников по компании
    @GetMapping("/bycomp")
    public String byComp(Model model, @RequestParam(name = "comp") String comp){
        CompanyDto companyDto = untitled1Client.getOneComp(comp.toUpperCase(Locale.ROOT));
        model.addAttribute("employees", companyDto.getEmployees());
        model.addAttribute("company", companyDto);
        return "bycomp";
    }
// Получение сотрудника по id
    @GetMapping("/finduserbyid")
    public String findUserById(Model model, @RequestParam(name = "id") int id){
        UserDto userDto = untitled1Client.getOneUser(id);
        if (userDto == null){
            model.addAttribute("notfound", "User not found!");
            return "index";
        }else {
            model.addAttribute("user", userDto);
        }
        return "oneuser";
    }
//  Генерация пользователей
    @GetMapping("/generate")
    public String generate(Model model){
        try {
            untitled1Client.generate();
            model.addAttribute("status", "100 Users created");
            return "index";
        }catch (Exception e){
            model.addAttribute("status", "Cant generate employees. Maybe no found \"by default\" company");
            return "index";
        }
    }
//  Получение всех компаний
    @GetMapping("/allcompany")
    public String getAllCompany(Model model){
        List<CompanyDto> list = untitled1Client.getAllCompany();
        model.addAttribute("complist", list);
        return "allcompany";
    }
//  Создание сотрудника
    @PostMapping("/createuser")
    public String createUser(Model model,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "company") String company,
                             @RequestParam(name = "department") String department,
                             @RequestParam(name = "salary") int salary,
                             @RequestParam(name = "sex") String sex){

        UserDto userDto;
        if(sex.equalsIgnoreCase("male")){
             userDto = new UserDto(name, company, department, salary, Sex.MALE);
        }else {
             userDto = new UserDto(name, company, department, salary, Sex.FEMALE);
        }

        untitled1Client.createUser(userDto);
        model.addAttribute("done", "User was created & save in BD!" );

        return "index";
    }
// Создание компании
    @PostMapping("/createcompany")
    public String createCompany(Model model,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "phonenumber") int phonenumber,
                                @RequestParam(name = "weburl") String weburl){

        CompanyDto companyDto = new CompanyDto(name.toUpperCase(Locale.ROOT), address, phonenumber, weburl);

        untitled1Client.createCompany(companyDto);
        model.addAttribute("doneComp", "Company was created & save in BD!" );

        return "index";

    }
//  Удаление сотрудника
    @PostMapping("/deleteuser")
    public String deleteUser(Model model, @RequestParam(name = "id") int id){
        untitled1Client.deleteUser(id);
        model.addAttribute("delu", "User was deleted");

        return "index";
    }
//  Удаление компании
    @PostMapping("/deletecompany")
    public String deleteCompany(Model model, @RequestParam(name = "comp") String comp){
        CompanyDto companyDto = untitled1Client.getOneComp(comp);
        if(companyDto==null){
            model.addAttribute("delc", "Company not found");
            return "index";
        }else {
            try{
                untitled1Client.deleteCompany(comp.toUpperCase(Locale.ROOT));
                model.addAttribute("delc", "Company was deleted");
                return "index";
            }catch (Exception e){
                model.addAttribute("delc", "Cant delete company. Maybe company have employee?");
                return "index";
            }
        }

    }
//  Удаление всех сотрудников компании
    @PostMapping("/deleteallusers")
    public String deleteAllUsers(Model model, @RequestParam(name = "comp") String comp){
        List<UserDto> userDtoList = untitled1Client.getUsersByComp(comp.toUpperCase(Locale.ROOT));
        for (UserDto userDto : userDtoList){
            untitled1Client.deleteUser(userDto.getId());
        }
        model.addAttribute("killuser", "All employees of " + comp.toUpperCase(Locale.ROOT) + " was killed");
        return "index";
    }


}
