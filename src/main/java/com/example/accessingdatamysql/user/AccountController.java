package com.example.accessingdatamysql.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class AccountController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    public @ResponseBody String loginUser(@RequestBody RequestLoggin request){
        List<Player> players = playerService.findByNickname(request.getNickname());
        if(players.size()==0){
            List<Admin> admins = adminService.findByNickname(request.getNickname());
            if(admins.size()==0){
                return "Invalid credentials";
            } else{
                Admin admin = admins.get(0);
                if(admin==null){
                    return "Invalid credentials";
                } else if(!admin.getPassword().equals(request.getPassword())){
                    return "Invalid credentials";
                } else{
                    return admin.getId().toString() + "Admin";
                }
            }
        } else{
            Player player = players.get(0);
            if(!player.getPassword().equals(request.getPassword())){
                return "Invalid credentials";
            } else{
                return player.getId().toString() + "Player";
            }
        }
    }

    @PostMapping(value = "/register")
    public @ResponseBody String registerUser(@RequestBody Player player){
        if(playerService.findByNickname(player.getNickname()).size()>0){
            return "Nickname already taken";
        }
        playerService.savePlayer(player);
        Player newPlayer = playerService.findByNickname(player.getNickname()).get(0);
        return newPlayer.getId().toString();
    }
}
