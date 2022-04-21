package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.UserDTO;
import dbc.vemser.refoundapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/saveUser")
    public UserDTO save(@Valid @RequestBody UserCreateDTO userCreate,@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
        return userService.save(userCreate, file);
    }

    @ApiOperation(value = "Retorna uma lista de usuarios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list users"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/listAllUser")
    public List<UserDTO> list(){
        return userService.list();
    }

    @ApiOperation(value = "Retorna um usuario atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update user"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/updateUser")
    public UserDTO update(@RequestParam Integer id,@Valid @RequestBody UserCreateDTO userAtt) throws Exception{
        return userService.update(id,userAtt);
    }

    @ApiOperation(value = "Retorna um usuario deletado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete user"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/deleteUser")
    public UserDTO delete(@RequestParam Integer id)throws Exception{
        return userService.delete(id);
    }

    @ApiOperation(value = "Retorna um usuario pelo nome pesquisado!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "find user by name"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/findUserByName")
    public List<UserDTO> findByNameContainingIgnoreCase(String name)throws Exception{
        return userService.findByNameContainingIgnoreCase(name);
    }
}
