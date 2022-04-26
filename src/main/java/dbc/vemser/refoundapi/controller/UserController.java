package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.user.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserDTO;
import dbc.vemser.refoundapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @PostMapping("/saveAdmin")
    public UserDTO saveAdmin(@Valid @RequestBody UserCreateDTO userCreate, @RequestParam Integer role) throws Exception {
        return userService.save(userCreate, role);
    }


    @ApiOperation(value = "Retorna uma pessoa criada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "save users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/saveUser")
    public UserDTO save(@Valid @RequestBody UserCreateDTO userCreate) throws Exception {
        return userService.save(userCreate, 4);
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
    @PutMapping("/updateUser")
    public UserDTO update(@RequestParam Integer id, @RequestParam(required = false) String password, @RequestParam(required = false) String file) throws Exception {
        return userService.update(id, password, file);
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
