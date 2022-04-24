package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.refund.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundUpdateDTO;
import dbc.vemser.refoundapi.service.RefundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refund")
@Validated
public class RefundController {

    private final RefundService refundService;

    @ApiOperation(value = "Retorna um reembolso criado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Refund Created"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping("/")
    public RefundDTO create(@RequestBody RefundCreateDTO refundCreate) {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return refundService.create(Integer.parseInt(id), refundCreate);
    }

    @ApiOperation(value = "Retorna uma lista de reembolsos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list all Refunds"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/")
    public List<RefundDTO> list() {
        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return refundService.list(Integer.parseInt(id));
    }

    @ApiOperation(value = "Retorna um refund atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update refund"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/")
    public RefundDTO update(@PathVariable Integer id, RefundUpdateDTO refundAtt) throws Exception {
        return refundService.update(id, refundAtt);
    }

    @ApiOperation(value = "Retorna um refund deletado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete refund"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/")
    public RefundDTO delete(@PathVariable Integer id) throws Exception {
        return refundService.delete(id);
    }

}
