package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JugadorController {
    private static final String VIEWS_JUGADOR_CREATE_FORM = "jugadores/createJugadorForm";

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService){
        this.jugadorService = jugadorService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/jugadores/new")
    public String iniCreationForm(Map<String, Object> model){
        Jugador jugador = new Jugador();
        model.put("jugador", jugador);
        return VIEWS_JUGADOR_CREATE_FORM;
    }

    @PostMapping(value = "/jugadores/new")
    public String processCreationForm(@Valid Jugador jugador, BindingResult result){
        if(jugador.getNombredeUsuario().strip()=="" || jugador.getEmail().strip()==""
            ||jugador.getPassword().strip()==""){
                result.rejectValue("nombredeUsuario", "CampoNulo" ,"Ningún campo puede estar vacio");
                return VIEWS_JUGADOR_CREATE_FORM;
        }
        if(this.jugadorService.findJugadorByName(jugador.getNombredeUsuario()).size()>0){
            result.rejectValue("nombredeUsuario", "nombreExiste","Nombre de Usuario ya existente");
            return VIEWS_JUGADOR_CREATE_FORM;
        }
        if(result.hasErrors()){
            return VIEWS_JUGADOR_CREATE_FORM;
        } else{
            this.jugadorService.saveJugador(jugador);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/jugadores/find")
    public String initFindForm(Map<String, Object> model){
        model.put("jugador", new Jugador());
        return "jugadores/findJugadores";
    }

    @GetMapping(value = "/jugadores")
    public String processFindForm(Jugador jugador, BindingResult result, Map<String, Object> model){
        
        if(jugador.getNombredeUsuario()==null){
            jugador.setNombredeUsuario("");
        }

        Collection<Jugador> results = this.jugadorService.findJugadorByName(jugador.getNombredeUsuario());
        if(results==null){
            result.rejectValue("nombredeUsuario", "No se encontro ningún usuario con ese nombre");
            return "jugadores/findJugadores";
        } else if(results.size()==1){
            jugador = results.iterator().next();
            return "redirect:/jugadores/" + jugador.getId();
        } else{
            model.put("selections", results);
            return "jugadores/jugadoresList";
        }
    }

    @PostMapping(value = "/jugadores/{jugadorId}/edit")
    public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
        @PathVariable("jugadorId") String nombredeUsuario){
            if(result.hasErrors()){
                return VIEWS_JUGADOR_CREATE_FORM;
            } else{
                jugador.setNombredeUsuario(nombredeUsuario);
                this.jugadorService.saveJugador(jugador);
                return "redirect:/jugadores/{jugadorId}";
            }
        }

    @GetMapping("/jugadores/{jugadorId}")
    public ModelAndView showJugador(@PathParam("jugadorId") Integer id){
        ModelAndView mav = new ModelAndView("jugadores/jugadorDetails");
        mav.addObject(this.jugadorService.findJugadorById(id));
        return mav;
    }
}
