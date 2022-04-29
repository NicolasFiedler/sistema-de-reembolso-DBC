package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.user.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserUpdateDTO;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Retorna uma pessoa criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "save users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/saveAdmin", consumes = {"multipart/form-data"})
    public UserDTO saveAdmin(@Valid @ModelAttribute UserCreateDTO userCreate, @RequestParam String role) throws Exception {
        return userService.save(userCreate, role);
    }


    @ApiOperation(value = "Retorna uma pessoa criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "save users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/saveUser", consumes = {"multipart/form-data"})
    public UserDTO save(@Valid @ModelAttribute UserCreateDTO userCreate) throws Exception {
        return userService.save(userCreate, "4");
    }



    @ApiOperation(value = "Retorna uma lista de usuarios cadastrados listados pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "order list by id users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/listAllUserOrderById")
    public Page<UserDTO> listOrderById(Integer requestPage,Integer sizePage) {
        return userService.listOrderById(requestPage,sizePage);
    }

    @ApiOperation(value = "Retorna um usuario atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update user"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/updateUser", consumes = {"multipart/form-data"})
    public UserDTO update(@Valid @ModelAttribute UserUpdateDTO userAtt) throws Exception {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.update(Integer.parseInt(id), userAtt);
    }

    @ApiOperation(value = "Retorna um usuario atualizado pelo admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update user by admin"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/updateAdmin", consumes = {"multipart/form-data"})
    public UserDTO updateAdmin(@Valid @ModelAttribute UserCreateDTO userAtt,@RequestParam String id,@RequestParam String role) throws BusinessRuleException {
        return userService.updateAdmin(id,userAtt,role);
    }

    @ApiOperation(value = "Retorna um usuario deletado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete user"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/deleteUser")
    public UserDTO delete(@RequestParam Integer id) throws Exception {
        return userService.delete(id);
    }

    @ApiOperation(value = "Retorna um usuario pelo nome pesquisado!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "find user by name"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/findUserByName")
    public List<UserDTO> findByNameContainingIgnoreCase(String name) throws Exception {
        return userService.findByNameContainingIgnoreCase(name);
    }

    @ApiOperation(value = "Retorna uma lista de usuario ordenada pelo nome!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "order by user name"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/orderByName")
    public Page<UserDTO> orderByName(Integer requestPage, Integer sizePage){
        return userService.orderByName(requestPage,sizePage);
    }
}
