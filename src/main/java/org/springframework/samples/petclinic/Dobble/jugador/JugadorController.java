package org.springframework.samples.petclinic.Dobble.jugador;

import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class JugadorController {
    private static final String VIEWS_JUGADOR_CRETE_FORM = "/juagdores/createJugadorForm";

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService){
        this.jugadorService = jugadorService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
    }

    @GetMapping(value = "/jugadores/new")
    public String iniCreationForm(Map<String, Object> model){
        Jugador jugador = new Jugador();
        model.put("jugador", jugador);
        return VIEWS_JUGADOR_CRETE_FORM;
    }

    @PostMapping(value = "/jugadores/new")
    public String processCreationForm(@Valid Jugador jugador, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_JUGADOR_CRETE_FORM;
        } else{
            this.jugadorService.saveJugador(jugador);
            return "redirect:/jugador" + jugador.getNombredeUsuario();
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
            model.put("all", this.jugadorService.findAll());
            return "jugadores/jugadoresList";
        }

        Jugador jugadorResult = this.jugadorService.findJugadorByName(jugador.getNombredeUsuario());
        if(jugadorResult==null){
            result.rejectValue("nombredeUsuario", "No se encontro ningún usuario con ese nombre");
            return "jugadores/findJugadores";
        } else{
            return "redirect:/jugadores/" + jugador.getId();
        }
    }

    @PostMapping(value = "/jugadores/{jugadorId}/edit")
    public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
        @PathVariable("jugadorId") String nombredeUsuario){
            if(result.hasErrors()){
                return VIEWS_JUGADOR_CRETE_FORM;
            } else{
                jugador.setNombredeUsuario(nombredeUsuario);
                this.jugadorService.saveJugador(jugador);
                return "redirect:/jugadores/{jugadorId}";
            }
        }

    @GetMapping("/jugadores/{jugadorId}")
    public ModelAndView showJugador(@PathParam("jugadorId") int id){
        ModelAndView mav = new ModelAndView("jugadores/jugadorDetails");
        mav.addObject(this.jugadorService.findJugadorById(id));
        return mav;
    }
}
