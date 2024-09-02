package prueba.backend.service;

import prueba.backend.dto.EmpleadoDto;
import prueba.backend.dto.RequestDto;
import prueba.backend.dto.RespuestaDto;

import java.util.List;

public interface IEmpleadoService {
    List<EmpleadoDto> listaEmpleado();
    RespuestaDto crearEmpleado(RequestDto request);
    RespuestaDto editarEmpleado(RequestDto request) throws Exception;
    RespuestaDto eliminarEmpleado(RequestDto request) throws Exception;
}
