package com.example.accessingdatamysql.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/api")
public class AccountController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    public @ResponseBody LogRegResponse loginUser(@RequestBody RequestLoggin request) throws BadRequest{
        List<Player> players = playerService.findByNickname(request.getNickname());
        if(players.size()==0){
            List<Admin> admins = adminService.findByNickname(request.getNickname());
            if(admins.size()==0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
            } else{
                Admin admin = admins.get(0);
                if(admin==null){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
                } else if(!admin.getPassword().equals(request.getPassword())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
                } else{
                    return new LogRegResponse(admin.getId(), "Admin");
                }
            }
        } else{
            Player player = players.get(0);
            if(!player.getPassword().equals(request.getPassword())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
            } else{
                return new LogRegResponse(player.getId(), "Player");
            }
        }
    }

    @PostMapping(value = "/register")
    public @ResponseBody LogRegResponse registerUser(@RequestBody Player player) throws BadRequest{
        if(playerService.findByNickname(player.getNickname()).size()>0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nickname already taken");
        }
        playerService.savePlayer(player);
        Player newPlayer = playerService.findByNickname(player.getNickname()).get(0);
        return new LogRegResponse(newPlayer.getId(), "Player");
    }
}
