/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmappers;

/**
 *
 * @author emmablomsterberg
 */
public class UserMapper {
    
    private String userName;
    private String role;
    
    public UserMapper(entity.User u) {
        userName = u.getUserName();
        role = u.getRolesAsStrings().get(0);
    }
}
