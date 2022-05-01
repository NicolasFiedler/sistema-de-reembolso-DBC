package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemUpdateDTO;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "Retorna um item criado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "save itens"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/{idRefund}", consumes = {"multipart/form-data"})
    public ItemDTO create(@PathVariable Integer idRefund, @Valid @ModelAttribute ItemCreateDTO itemCreate) throws Exception {
        return itemService.create(idRefund, itemCreate);
    }

    @ApiOperation(value = "Retorna um item criado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get item"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/{id}")
    public ItemDTO getById(@PathVariable Integer id) throws BusinessRuleException {
        return itemService.getItemById(id);
    }

    @ApiOperation(value = "Retorna um item atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update itens"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(value = "/updateItem/{id}", consumes = {"multipart/form-data"})
    public ItemDTO update(@PathVariable Integer id, @Valid @ModelAttribute ItemUpdateDTO itemAtt) {
        return itemService.update(id, itemAtt);
    }

    @ApiOperation(value = "Retorna uma lista de todos os itens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list itens"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/listAllItens")
    public List<ItemDTO> list() {
        return itemService.list();
    }

    @ApiOperation(value = "Retorna um item deletado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete itens"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/deleteItem")
    public ItemDTO delete(Integer id) {
        return itemService.delete(id);
    }

}