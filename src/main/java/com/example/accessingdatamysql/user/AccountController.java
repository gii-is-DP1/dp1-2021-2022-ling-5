package com.example.accessingdatamysql.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.accessingdatamysql.figure.FigureService;
import com.example.accessingdatamysql.role.RoleService;

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

    @Autowired
    private FigureService figureService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/login")
    public @ResponseBody LogRegResponse loginUser(@RequestBody RequestLoggin request, HttpServletRequest req)
            throws BadRequest {
        HttpSession session = req.getSession();
        System.out.println(session);
        List<Player> players = playerService.findByNickname(request.getNickname());
        if (players.size() == 0) {
            List<Admin> admins = adminService.findByNickname(request.getNickname());
            if (admins.size() == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
            } else {
                Admin admin = admins.get(0);
                if (admin == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
                } else if (!admin.getPassword().equals(request.getPassword())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
                } else {
                    Long id = admin.getId();
                    String nickname = admin.getNickname();
                    session.setAttribute("nickname", nickname);
                    return new LogRegResponse(id, "Admin");
                }
            }
        } else {
            Player player = players.get(0);
            if (!player.getPassword().equals(request.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
            } else {
                Long id = player.getId();
                String nickname = player.getNickname();
                session.setAttribute("nickname", nickname);
                return new LogRegResponse(id, "Player");
            }
        }
    }

    @PostMapping(value = "/register")
    public @ResponseBody LogRegResponse registerUser(@RequestBody Player player, HttpServletRequest req) throws BadRequest {
        HttpSession session = req.getSession();
        System.out.println(session);
        if (playerService.findByNickname(player.getNickname()).size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nickname already taken");
        }
        player.setFigure(figureService.findFigure(3L).get());
        player.setRole(roleService.findRole(1L).get());
        String nickname = player.getNickname();
        session.setAttribute("nickname", nickname);
        playerService.savePlayer(player);
        Player newPlayer = playerService.findByNickname(player.getNickname()).get(0);
        return new LogRegResponse(newPlayer.getId(), "Player");
    }
}
