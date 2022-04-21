package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.RefundDTO;
import dbc.vemser.refoundapi.service.RefundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/createRefund")
    public RefundDTO create(@RequestBody RefundCreateDTO refundCreate) {
        return refundService.create(refundCreate);
    }

    @ApiOperation(value = "Retorna uma lista de reembolsos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list all Refunds"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/listAllRefunds")
    public List<RefundDTO> list() {
        return refundService.list();
    }

    @ApiOperation(value = "Retorna um refund atualizado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update refund"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("/updateRefund")
    public RefundDTO update(@PathVariable Integer id, RefundCreateDTO refundAtt) throws Exception {
        return refundService.update(id, refundAtt);
    }

    @ApiOperation(value = "Retorna um refund deletado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "delete refund"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("/deleteRefund")
    public RefundDTO delete(@PathVariable Integer id) throws Exception {
        return refundService.delete(id);
    }

}
