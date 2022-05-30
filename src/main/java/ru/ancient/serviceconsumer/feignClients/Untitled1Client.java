package ru.ancient.serviceconsumer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ancient.serviceconsumer.DTO.CompanyDto;
import ru.ancient.serviceconsumer.DTO.UserDto;

import java.util.List;

@FeignClient(value = "untitled1client", url = "localhost:5555/api")
public interface Untitled1Client {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<UserDto> getAllUsers();

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    UserDto getOneUser(@PathVariable int id);

    @RequestMapping(value = "/users/bycompany/{comp}", method = RequestMethod.GET)
    List<UserDto> getUsersByComp(@PathVariable String comp);

    @RequestMapping(value = "/companies/{companyName}", method = RequestMethod.GET)
    CompanyDto getOneComp(@PathVariable String companyName);

    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    List<CompanyDto> getAllCompany();

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    String generate();

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    void createUser(UserDto userDto);

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    void createCompany(CompanyDto companyDto);

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable int id);

    @RequestMapping(value = "/companies/{comp}", method = RequestMethod.DELETE)
    void deleteCompany(@PathVariable String comp);
}
