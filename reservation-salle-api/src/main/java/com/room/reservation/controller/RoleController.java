package com.room.reservation.controller;

import com.room.reservation.model.Role;
import com.room.reservation.service.IBaseService;
import com.room.reservation.service.face.IRoleService;
import com.room.reservation.web.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
@RequestMapping(path = "/api/role")
public class RoleController extends BaseController<Role> {
    @Autowired
    private IRoleService iRoleService;

    public RoleController(IBaseService<Role> baseService) {
        super(baseService);
    }

    @GetMapping("/getbyname/{username}")
    public Response getRoleByName(@PathVariable String name) {
        Role r = null;
        try {
            r = iRoleService.getByName(name);
        } catch (Exception e) {
            return new Response(1,null);
        }
        return new Response(0, r);
    }

    @GetMapping("/roles")
    public Response getRoles() {
        List<Role> r = null;
        try {
            r = iRoleService.findAll();
        } catch (Exception e) {
            return new Response(1,null);
        }
        return new Response(0, r);
    }
}
