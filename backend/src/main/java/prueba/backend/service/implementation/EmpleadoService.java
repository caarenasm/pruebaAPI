package prueba.backend.service.implementation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.backend.dto.EmpleadoDto;
import prueba.backend.dto.RequestDto;
import prueba.backend.dto.RespuestaDto;
import prueba.backend.jpa.entity.EmpleadoEntity;
import prueba.backend.jpa.repository.EmpleadoRepository;
import prueba.backend.service.IEmpleadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public List<EmpleadoDto> listaEmpleado() {
        List<EmpleadoEntity> lista = empleadoRepository.findAll();
        List<EmpleadoDto> resultado = new ArrayList<>();
        for(EmpleadoEntity iteraccion:lista) {
            EmpleadoDto data = new EmpleadoDto();
            data.setId(iteraccion.getId());
            data.setNombre(iteraccion.getNombre());
            data.setDireccion(iteraccion.getDireccion());
            data.setSalario(iteraccion.getSalario());
            data.setTelefono(iteraccion.getTelefono());
            resultado.add(data);
        }
        return resultado;
    }

    @Override
    public RespuestaDto crearEmpleado(RequestDto request) {
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setNombre(request.getNombre());
        empleado.setDireccion(request.getDireccion());
        empleado.setSalario(request.getSalario());
        empleado.setTelefono(request.getTelefono());
        empleadoRepository.save(empleado);

        RespuestaDto respuesta = new RespuestaDto();
        respuesta.setMensajes("empleado creado con exito");
        return respuesta;
    }

    @Override
    public RespuestaDto editarEmpleado(RequestDto request) throws Exception {

        Optional<EmpleadoEntity> consulta = empleadoRepository.findById(request.getId());

        if(consulta.isPresent()){
            EmpleadoEntity empleado = consulta.get();
            empleado.setNombre(request.getNombre());
            empleado.setDireccion(request.getDireccion());
            empleado.setSalario(request.getSalario());
            empleado.setTelefono(request.getTelefono());
            empleadoRepository.save(empleado);

            RespuestaDto respuesta = new RespuestaDto();
            respuesta.setMensajes("empleado editado con exito");
            return respuesta;
        }else{
            throw new Exception("Empleado no existe");
        }

    }

    @Override
    public RespuestaDto eliminarEmpleado(RequestDto request) throws Exception {
        Optional<EmpleadoEntity> consulta = empleadoRepository.findById(request.getId());

        if(consulta.isPresent()){
            EmpleadoEntity empleado = consulta.get();
            empleadoRepository.delete(empleado);

            RespuestaDto respuesta = new RespuestaDto();
            respuesta.setMensajes("empleado eliminado con exito");
            return respuesta;
        }else{
            throw new Exception("Empleado no existe");
        }
    }
}
